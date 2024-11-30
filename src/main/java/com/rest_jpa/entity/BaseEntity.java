package com.rest_jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

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

    @CreatedDate
    @Column(name = "created", nullable = false, insertable = false, updatable = false)
    protected LocalDateTime created;

    @Column(name = "updated", nullable = false, insertable = false)
    protected LocalDateTime updated;

    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "description")
    protected String description;

    public BaseEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @PreUpdate
    protected void onUpdate() {
        updated = LocalDateTime.now();
    }
}
