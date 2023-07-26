package com.github.eemrecvl.document.processor.service;

import com.github.eemrecvl.document.common.model.Document;
import com.github.eemrecvl.document.processor.model.UploadedDocument;

import java.util.Map;

public interface DocumentProcessor {
    Map<String, Document> mergeDocumentData(UploadedDocument uploadedDocument);
}
