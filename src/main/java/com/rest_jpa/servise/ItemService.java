package com.rest_jpa.servise;

import com.rest_jpa.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    Item create(Item item);
    void update(Item item);
    void delete(long id);
    List<Item> findAll();
    Item findById(long id);
}
