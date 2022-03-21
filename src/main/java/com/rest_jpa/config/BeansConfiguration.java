package com.rest_jpa.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration implements InitializingBean {

    private ObjectMapper mapper;

    public BeansConfiguration(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
