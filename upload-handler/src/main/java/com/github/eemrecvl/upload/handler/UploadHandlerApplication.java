package com.github.eemrecvl.upload.handler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@ComponentScan(basePackages = "com.github.eemrecvl")
@EnableKafka
public class UploadHandlerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadHandlerApplication.class, args);
    }
}
