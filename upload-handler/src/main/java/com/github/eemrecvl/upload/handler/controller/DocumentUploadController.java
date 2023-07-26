package com.github.eemrecvl.upload.handler.controller;

import com.github.eemrecvl.upload.handler.model.DocumentUploadRequest;
import com.github.eemrecvl.upload.handler.service.DocumentUploadHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DocumentUploadController {

    private final DocumentUploadHandler documentUploadHandler;

    public DocumentUploadController(DocumentUploadHandler documentUploadHandler) {
        this.documentUploadHandler = documentUploadHandler;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadDocument(@RequestBody DocumentUploadRequest documentUploadRequest) {
        documentUploadHandler.handleDocumentUpload(documentUploadRequest);
        return ResponseEntity.ok("OK");
    }
}
