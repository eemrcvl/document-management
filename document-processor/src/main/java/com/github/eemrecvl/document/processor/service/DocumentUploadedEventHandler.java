package com.github.eemrecvl.document.processor.service;

import com.github.eemrecvl.document.common.kafka.model.DocumentUploadedEvent;

public interface DocumentUploadedEventHandler {
    void handleDocumentUploadedEvent(DocumentUploadedEvent documentUploadedEvent);
}
