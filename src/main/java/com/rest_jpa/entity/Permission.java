package com.rest_jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "permissions")
public class Permission extends BaseEntity{
}
