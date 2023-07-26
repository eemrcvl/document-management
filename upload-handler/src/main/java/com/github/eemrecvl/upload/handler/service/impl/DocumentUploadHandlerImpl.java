package com.github.eemrecvl.upload.handler.service.impl;

import com.github.eemrecvl.document.common.kafka.model.DocumentUploadedEvent;
import com.github.eemrecvl.document.common.kafka.producer.service.DocumentUploadedMessageProducer;
import com.github.eemrecvl.upload.handler.model.DocumentUploadRequest;
import com.github.eemrecvl.upload.handler.service.DocumentUploadHandler;
import com.github.eemrecvl.upload.handler.service.S3DocumentDataBucket;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class DocumentUploadHandlerImpl implements DocumentUploadHandler {
    private final S3DocumentDataBucket s3DocumentDataBucket;
    private final DocumentUploadedMessageProducer documentUploadedMessageProducer;

    public DocumentUploadHandlerImpl(S3DocumentDataBucket s3DocumentDataBucket,
                                     DocumentUploadedMessageProducer documentUploadedMessageProducer) {
        this.s3DocumentDataBucket = s3DocumentDataBucket;
        this.documentUploadedMessageProducer = documentUploadedMessageProducer;
    }

    @Override
    public void handleDocumentUpload(DocumentUploadRequest request) {
        URL url = s3DocumentDataBucket.saveUploadedDocumentToBucket(request);

        DocumentUploadedEvent event = new DocumentUploadedEvent();
        event.setId(request.getId());
        event.setUrl(url.toString());
        event.setCreatedAt(System.currentTimeMillis());
        documentUploadedMessageProducer.send(event);
    }
}
