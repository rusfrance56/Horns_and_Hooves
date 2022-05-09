package com.rest_jpa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
@Configuration
public class ApplicationProperties {

    @Value("${upload.path}")
    private String uploadPath;

    public String getUploadPath() {
        return uploadPath;
    }
}
