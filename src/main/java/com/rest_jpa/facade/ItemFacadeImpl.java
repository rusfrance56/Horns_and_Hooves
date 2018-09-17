package com.rest_jpa.facade;

import com.rest_jpa.entity.Item;
import com.rest_jpa.entity.to.ItemTO;
import com.rest_jpa.servise.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemFacadeImpl implements ItemFacade {

    @Autowired
    private ItemService itemService;

    @Override
    public ItemTO create(ItemTO to) {
        Item item = new Item(to);
        to.setId(itemService.create(item).getId());
        return to;
    }

    @Override
    public void update(ItemTO to) {
        Item item = itemService.findById(to.getId());
        if (item != null) {
            item.setId(to.getId());
            item.setName(to.getName());
            item.setImageUrl(to.getImageUrl());
            item.setCost(to.getCost());
            itemService.update(item);
        }
    }

    @Override
    public void delete(long id) {
        itemService.delete(id);
    }

    @Override
    public List<ItemTO> findAll() {
        List<Item> all = itemService.findAll();
        return all.stream().map(ItemTO::new).collect(Collectors.toList());
    }

    @Override
    public ItemTO findById(long id) {
        Item item = itemService.findById(id);
        return new ItemTO(item);
    }
}
