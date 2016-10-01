package com.rest_jpa.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by User on 01.10.2016.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter implements WebMvcConfigurer {

    private static final String STATIC_FILE_PATH = "src/main/resources/static";

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        try {
            Files.walk(Paths.get(STATIC_FILE_PATH), new FileVisitOption[0])
                    .filter(Files::isRegularFile)
                    .map(f -> f.toString())
                    .map(s -> s.substring(STATIC_FILE_PATH.length()))
                    .map(s -> s.replaceAll("\\.html", ""))
                    .forEach(p -> registry.addViewController(p).setViewName(p));

        } catch (IOException e) {
            e.printStackTrace();
        }

        registry.addViewController("/").setViewName("index");
    }

}