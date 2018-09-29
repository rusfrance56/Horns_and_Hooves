package com.rest_jpa.servise;

import com.rest_jpa.entity.CustomerOrder;
import com.rest_jpa.entity.Item;
import com.rest_jpa.exceptions.ApplicationException;
import com.rest_jpa.repository.CustomerOrderRepository;
import com.rest_jpa.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.rest_jpa.exceptions.ApplicationException.checkArgument;
import static com.rest_jpa.exceptions.ApplicationException.checkNotNullAndNotEmpty;
import static com.rest_jpa.exceptions.ErrorKey.*;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Override
    public Item create(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void update(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void delete(long id) {
        List<CustomerOrder> orders = customerOrderRepository.findByItems_Id(id);
        checkArgument(orders.isEmpty(), ITEM_PRESENT_IN_SOME_CUSTOMER_ORDERS, id);
        itemRepository.deleteById(id);
    }

    @Override
    public List<Item> findAll() {
        return checkNotNullAndNotEmpty(itemRepository.findAll(), ITEMS_NOT_FOUND);
    }

    @Override
    public Item findById(long id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.orElseThrow(() -> new ApplicationException(ITEM_NOT_FOUND, id));
    }
}
