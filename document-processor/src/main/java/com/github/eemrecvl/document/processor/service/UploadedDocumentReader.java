package com.github.eemrecvl.document.processor.service;

import com.github.eemrecvl.document.common.model.UploadedDocumentReadParams;
import com.github.eemrecvl.document.processor.model.UploadedDocument;

public interface UploadedDocumentReader<T extends UploadedDocumentReadParams> {
    UploadedDocument readDocument(T readParams);
}
