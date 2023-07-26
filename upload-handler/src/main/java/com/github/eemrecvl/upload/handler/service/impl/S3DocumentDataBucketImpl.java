package com.github.eemrecvl.upload.handler.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.eemrecvl.document.common.aws.config.AWSS3Config;
import com.github.eemrecvl.upload.handler.exception.DocumentUploadRequestException;
import com.github.eemrecvl.upload.handler.model.DocumentUploadRequest;
import com.github.eemrecvl.upload.handler.service.S3DocumentDataBucket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
@Slf4j
public class S3DocumentDataBucketImpl implements S3DocumentDataBucket {
    private final AmazonS3 s3client;
    private final AWSS3Config awsConfig;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public S3DocumentDataBucketImpl(AmazonS3 s3client,
                                    AWSS3Config awsConfig) {
        this.s3client = s3client;
        this.awsConfig = awsConfig;
    }

    @Override
    public URL saveUploadedDocumentToBucket(DocumentUploadRequest request) {
        createBucketIfNotExists();
        String json;
        try {
            json = objectMapper.writeValueAsString(request);
        } catch (Exception e) {
            log.error("could not convert request to json!", e);
            throw new DocumentUploadRequestException("could not convert request to json!");
        }
        s3client.putObject(awsConfig.getBucketName(), request.getId(), json);
        return s3client.getUrl(awsConfig.getBucketName(), request.getId());
    }

    private void createBucketIfNotExists() {
        if(!s3client.doesBucketExistV2(awsConfig.getBucketName())) {
            log.info("bucket {} does not exist!, creating...", awsConfig.getBucketName());
            s3client.createBucket(awsConfig.getBucketName());
        }
    }
}
