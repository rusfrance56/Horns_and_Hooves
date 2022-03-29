package com.rest_jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Access(AccessType.FIELD)
@Data
@NoArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(value = AccessType.PROPERTY)
    protected Long id;

    @Column(name = "created", nullable = false, insertable = false, updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    protected LocalDateTime created;

    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "description")
    protected String description;

    public BaseEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
