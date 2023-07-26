package com.github.eemrecvl.document.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class S3BucketDocumentReadParams extends UploadedDocumentReadParams {
    private String url;
}
