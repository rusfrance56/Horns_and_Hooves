package com.rest_jpa.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@PropertySource("classpath:application.properties")
@Configuration
public class ApplicationProperties {

    @Value("${upload.path}")
    private String uploadPath;

}
