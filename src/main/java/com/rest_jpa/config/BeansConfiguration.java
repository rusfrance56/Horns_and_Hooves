package com.rest_jpa.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.MultipartConfigElement;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableJpaAuditing
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
        StandardServletMultipartResolver commonsMultipartResolver = new StandardServletMultipartResolver();
//        commonsMultipartResolver.setMaxUploadSize(100000*100*4);/*4Mb*/ TODO refactoring
//        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        return commonsMultipartResolver;
    }

    @Bean
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
