package com.rest_jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "permissions")
@Data
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseEntity{
}
