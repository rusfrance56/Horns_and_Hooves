package com.rest_jpa.entity;

import com.rest_jpa.entity.to.ItemTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(schema = "public", name = "item")
public class Item extends BaseEntity {

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "cost")
    private Double cost;

//    @ManyToMany(mappedBy = "items")
//    private List<CustomerOrder> customerOrders;

    public Item() {
    }

    public Item(ItemTO to) {
        this.name = to.getName();
        this.imageUrl = to.getImageUrl();
        this.cost = to.getCost();
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

//    public List<CustomerOrder> getCustomerOrders() {
//        return customerOrders;
//    }

    @Override
    public String toString() {
        return "Item{" +
                "imageUrl='" + imageUrl + '\'' +
                ", cost=" + cost +
//                ", customerOrders=" + customerOrders +
                ", id=" + id +
                ", created=" + created +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
