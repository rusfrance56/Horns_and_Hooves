package com.rest_jpa.entity;

import javax.persistence.Column;

public class Item extends BaseEntity {

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "cost")
    private Double cost;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Item{" +
                "imageUrl='" + imageUrl + '\'' +
                ", cost=" + cost +
                ", created=" + created +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
