package com.github.eemrecvl.document.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.github.eemrecvl")
public class DocumentProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocumentProcessorApplication.class, args);
    }
}
