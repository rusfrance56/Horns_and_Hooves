package com.rest_jpa.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.MultipartConfigElement;

@Configuration
public class BeansConfiguration implements InitializingBean {

    private ObjectMapper mapper;

    @Autowired
    public BeansConfiguration(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void afterPropertiesSet() {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        return new MultipartConfigElement("");
    }

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(100000*100*4);/*4Mb*/
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        return commonsMultipartResolver;
    }
}
