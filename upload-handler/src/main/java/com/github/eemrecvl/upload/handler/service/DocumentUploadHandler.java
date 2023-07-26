package com.github.eemrecvl.upload.handler.service;

import com.github.eemrecvl.upload.handler.model.DocumentUploadRequest;

public interface DocumentUploadHandler {
    void handleDocumentUpload(DocumentUploadRequest request);
}
