package com.rest_jpa.utils;

import com.google.common.collect.ImmutableMap;
import com.rest_jpa.enumTypes.Department;
import com.rest_jpa.enumTypes.OrderStatus;
import com.rest_jpa.enumTypes.TaskPriority;
import com.rest_jpa.enumTypes.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enum")
public class EnumController {

    private final static Logger log = LoggerFactory.getLogger(EnumController.class);

    private static Map<String, Class<? extends Enum<?>>> ENUM_MAP = ImmutableMap.<String, Class<? extends Enum<?>>>builder()
            .put("Department", Department.class)
            .put("OrderStatus", OrderStatus.class)
            .put("TaskPriority", TaskPriority.class)
            .put("TaskStatus", TaskStatus.class)
            .build();

    private static <T extends Enum<T>> List<String> getEnumValuesByClass(String enumName) {
        @SuppressWarnings("unchecked") Class<T> elementType = (Class<T>) ENUM_MAP.get(enumName);
        return EnumSet.allOf(elementType).stream()
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/get")
    public ResponseEntity<List<String>> get(@RequestParam String enumName) {
        if (ENUM_MAP.containsKey(enumName)) {
            return new ResponseEntity<>(getEnumValuesByClass(enumName), HttpStatus.OK);
        }
        String errorMsg = "Wrong enum " + enumName;
        log.error(errorMsg);
        throw new IllegalArgumentException(errorMsg);
    }
}
