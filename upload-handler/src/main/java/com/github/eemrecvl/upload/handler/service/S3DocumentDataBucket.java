package com.github.eemrecvl.upload.handler.service;

import com.github.eemrecvl.upload.handler.model.DocumentUploadRequest;

import java.net.URL;

public interface S3DocumentDataBucket {
    URL saveUploadedDocumentToBucket(DocumentUploadRequest request);
}
