package com.github.eemrecvl.upload.handler.model;

import com.github.eemrecvl.document.common.model.Document;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentUploadRequest {
    private String id = UUID.randomUUID().toString();
    private List<Document> documents;
}
