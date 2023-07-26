package com.github.eemrecvl.document.common.kafka.producer.service;

import com.github.eemrecvl.document.common.kafka.model.DocumentUploadedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class DocumentUploadedMessageProducer {
    private final KafkaTemplate<String, Serializable> kafkaTemplate;

    public DocumentUploadedMessageProducer(KafkaTemplate<String, Serializable> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(DocumentUploadedEvent documentUploadedEvent) {
        CompletableFuture<SendResult<String, Serializable>> future = kafkaTemplate.send("document", documentUploadedEvent);

        future.whenComplete((result, exception) -> {
            if(exception == null) {
                log.info("Sent message {} with offset: {}", documentUploadedEvent, result.getRecordMetadata().offset());
            } else {
                log.error("Message {} was not sent due to error: {}", documentUploadedEvent, exception.getMessage());
            }
        });
    }
}
