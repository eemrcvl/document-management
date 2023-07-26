package com.github.eemrecvl.document.processor.service.impl;

import com.github.eemrecvl.document.common.model.Document;
import com.github.eemrecvl.document.processor.model.UploadedDocument;
import com.github.eemrecvl.document.processor.service.DocumentProcessor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *  Merge documentData for all pages of an documentCode
 *  Ideally there should be a document processor which creates a single document from each uploaded page
 */
@Service
public class DummyDocumentProcessor implements DocumentProcessor {

    @Override
    public Map<String, Document> mergeDocumentData(UploadedDocument uploadedDocument) {
        Map<String, Document> documentMap = new HashMap<>();
        for (Document document: uploadedDocument.getDocuments()) {
            if(!documentMap.containsKey(document.getDocumentCode())) {
                Document newDocument = new Document();
                newDocument.setPageNumber(-1);
                newDocument.setDocumentData(document.getDocumentData());
                newDocument.setDocumentCode(document.getDocumentCode());
                documentMap.put(document.getDocumentCode(), newDocument);
            } else {
                Document documentDataToUpdate = documentMap.get(document.getDocumentCode());
                String documentData = documentDataToUpdate.getDocumentData();
                documentData = documentData + document.getDocumentData();
                documentDataToUpdate.setDocumentData(documentData);
                documentMap.put(document.getDocumentCode(), documentDataToUpdate);
            }
        }
        return documentMap;
    }
}
