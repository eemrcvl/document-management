package com.github.eemrecvl.document.processor.model;


import com.github.eemrecvl.document.common.model.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadedDocument {
    private String id;
    private List<Document> documents;
}
