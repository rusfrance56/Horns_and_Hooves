package com.rest_jpa.entity;

import com.rest_jpa.enumTypes.ERole;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(value = AccessType.PROPERTY)
    protected Long id;

    @CreatedDate
    protected LocalDateTime created;

    protected LocalDateTime updated;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    protected ERole name;

    @Column(name = "description")
    protected String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roles_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;

    @PreUpdate
    protected void onUpdate() {
        updated = LocalDateTime.now();
    }
}
