package com.rest_jpa.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JsonTest implements CommandLineRunner {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println();
    }
}
