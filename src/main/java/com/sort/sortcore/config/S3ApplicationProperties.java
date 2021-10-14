package com.sort.sortcore.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
//@ConfigurationProperties("application.properties")
@Configuration
public class S3ApplicationProperties {

    private AwsServices awsServices;

    @Data
    public static class AwsServices {
        private String bucketName;
    }
}