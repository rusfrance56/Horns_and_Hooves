package com.rest_jpa.entity.to;

import com.rest_jpa.entity.Item;

public class ItemTO {
    private long id;
    private String name;
    private String description;
    private String imageUrl;
    private double cost;

    public ItemTO() {
    }

    public ItemTO(long id, String name, String description, String imageUrl, double cost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.cost = cost;
    }

    public ItemTO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.imageUrl = item.getImageUrl();
        this.cost = item.getCost();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
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
