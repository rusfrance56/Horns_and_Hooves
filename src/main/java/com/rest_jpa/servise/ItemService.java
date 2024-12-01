package com.rest_jpa.servise;

import com.rest_jpa.entity.Item;
import com.rest_jpa.entity.to.ItemTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    Item create(Item item);
    void update(Item item);
    void delete(long id);
    List<Item> findAll();
    Page<Item> findPageByFilter(String filter, Pageable pageable);
    Item findById(long id);
    List<Item> findAllByIds(List<Long> itemIds);
    Page<Item> findPageByFilter(ItemTO itemTO, Pageable pageRequest);
}
