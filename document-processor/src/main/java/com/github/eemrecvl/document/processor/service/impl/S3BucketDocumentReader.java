package com.github.eemrecvl.document.processor.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3URI;
import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.eemrecvl.document.common.model.S3BucketDocumentReadParams;
import com.github.eemrecvl.document.common.model.UploadedDocumentReadParams;
import com.github.eemrecvl.document.processor.model.UploadedDocument;
import com.github.eemrecvl.document.processor.service.UploadedDocumentReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@Slf4j
public class S3BucketDocumentReader implements UploadedDocumentReader<S3BucketDocumentReadParams> {

    private final AmazonS3 s3client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public S3BucketDocumentReader(AmazonS3 s3client) {
        this.s3client = s3client;
    }

    @Override
    public UploadedDocument readDocument(S3BucketDocumentReadParams uploadedDocumentReadParams) {
        try {
            URI uri = new URI(uploadedDocumentReadParams.getUrl());
            AmazonS3URI amazonS3URI = new AmazonS3URI(uri);
            S3Object s3Object = s3client.getObject(amazonS3URI.getBucket(), amazonS3URI.getKey());
            return objectMapper.readValue(s3Object.getObjectContent(), UploadedDocument.class);
        } catch (Exception ex) {
            log.error("could not get document", ex);
            throw new RuntimeException("could not get document");
        }
    }
}
