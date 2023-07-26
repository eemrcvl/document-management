package com.github.eemrecvl.document.common.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "kafka")
public class KafkaConfigData {
    private String bootstrapAddress;
    private String groupId;
    private String keyDeserializerClass;
    private String valueDeserializerClass;
    private String schemaRegistryUrl;
    private String schemaRegistryUrlKey;
    private String keySerializerClass;
    private String valueSerializerClass;
}
