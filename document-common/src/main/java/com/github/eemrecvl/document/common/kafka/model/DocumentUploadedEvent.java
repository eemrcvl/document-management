package com.github.eemrecvl.document.common.kafka.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class DocumentUploadedEvent implements Serializable {
    private String id;
    private String url;
    private long createdAt;
}
