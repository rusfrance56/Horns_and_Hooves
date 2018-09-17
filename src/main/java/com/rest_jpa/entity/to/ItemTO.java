package com.rest_jpa.entity.to;

import com.rest_jpa.entity.Item;

public class ItemTO {
    private Long id;
    private String name;
    private String imageUrl;
    private Double cost;

    public ItemTO() {
    }

    public ItemTO(Long id, String name, String imageUrl, Double cost) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.cost = cost;
    }

    public ItemTO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.imageUrl = item.getImageUrl();
        this.cost = item.getCost();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        return "ItemTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", cost=" + cost +
                '}';
    }
}
