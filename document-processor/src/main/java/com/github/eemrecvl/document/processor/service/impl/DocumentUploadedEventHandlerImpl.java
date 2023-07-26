package com.github.eemrecvl.document.processor.service.impl;

import com.github.eemrecvl.document.common.kafka.model.DocumentUploadedEvent;
import com.github.eemrecvl.document.common.model.Document;
import com.github.eemrecvl.document.common.model.S3BucketDocumentReadParams;
import com.github.eemrecvl.document.processor.model.UploadedDocument;
import com.github.eemrecvl.document.processor.service.DocumentProcessor;
import com.github.eemrecvl.document.processor.service.DocumentUploadedEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class DocumentUploadedEventHandlerImpl implements DocumentUploadedEventHandler {

    private final S3BucketDocumentReader s3BucketDocumentReader;
    private final DocumentProcessor documentProcessor;

    public DocumentUploadedEventHandlerImpl(S3BucketDocumentReader s3BucketDocumentReader,
                                            DocumentProcessor documentProcessor) {
        this.s3BucketDocumentReader = s3BucketDocumentReader;
        this.documentProcessor = documentProcessor;
    }

    @Override
    @KafkaListener(topics = "document", containerFactory = "kafkaListenerContainerFactory")
    public void handleDocumentUploadedEvent(DocumentUploadedEvent documentUploadedEvent) {
        log.info("received event: {}", documentUploadedEvent);
        S3BucketDocumentReadParams readParams = new S3BucketDocumentReadParams(documentUploadedEvent.getUrl());
        UploadedDocument uploadedDocument = s3BucketDocumentReader.readDocument(readParams);
        Map<String, Document> mergedDocuments = documentProcessor.mergeDocumentData(uploadedDocument);
        log.info("mergedDocuments: {}", mergedDocuments);
    }
}
