package com.rest_jpa.entity;

import com.rest_jpa.entity.to.ItemTO;
import com.rest_jpa.enumTypes.Department;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "items")
@Data
@NoArgsConstructor
public class Item extends BaseEntity {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "item_images", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "name")
    private List<String> imageUrls = new ArrayList<>();

    @Column(name = "department")
    @Enumerated(EnumType.STRING)
    private Department department;

    @Column(name = "price")
    private Double price;

    public Item(ItemTO to) {
        super(to.getName(), to.getDescription());
        this.imageUrls = to.getImageUrls();
        this.department = to.getDepartment();
        this.price = to.getPrice();
    }

    public static Item updateEntityFromTO(Item entity, ItemTO to) {
        entity.setName(to.getName());
        entity.setDescription(to.getDescription());
        entity.setImageUrls(to.getImageUrls());
        entity.setDepartment(to.getDepartment());
        entity.setPrice(to.getPrice());
        return entity;
    }
}
