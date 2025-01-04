package com.rest_jpa.servise;

import com.rest_jpa.entity.CustomerOrder;
import com.rest_jpa.entity.Item;
import com.rest_jpa.entity.to.ItemTO;
import com.rest_jpa.exceptions.ApplicationException;
import com.rest_jpa.fileUploading.FileSystemStorageService;
import com.rest_jpa.repository.CustomerOrderRepository;
import com.rest_jpa.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.rest_jpa.exceptions.ApplicationException.checkArgument;
import static com.rest_jpa.exceptions.ApplicationException.checkNotNullAndNotEmpty;
import static com.rest_jpa.exceptions.ErrorKey.*;
import static com.rest_jpa.repository.ItemRepository.Specs.byDepartment;
import static com.rest_jpa.repository.ItemRepository.Specs.byPriceGreaterThanEqual;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    private CustomerOrderRepository customerOrderRepository;
    private FileSystemStorageService fileSystemStorageService;

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
        Optional<Item> item = itemRepository.findById(id);
        checkArgument(item.isPresent(), ITEM_NOT_FOUND, id);

        List<CustomerOrder> orders = customerOrderRepository.findByItems_Id(id);
        checkArgument(orders.isEmpty(), ITEM_PRESENT_IN_SOME_CUSTOMER_ORDERS, id);

        if (fileSystemStorageService.deleteAll(item.get().getImageNames())) {
            itemRepository.deleteById(id);
        }
    }

    @Override
    public List<Item> findAll() {
        return checkNotNullAndNotEmpty(itemRepository.findAllByOrderByUpdatedDesc(), ITEMS_NOT_FOUND);
//        return checkNotNullAndNotEmpty(itemRepository.findAll(), ITEMS_NOT_FOUND);
    }

    @Override
    public Page<Item> findPageByFilter(String filter, Pageable pageable) {
        Page<Item> items;
        if (Strings.isBlank(filter)) {
            items = itemRepository.findAll(pageable);
        } else {
            items = itemRepository.findPageByNameOrDescription(filter, pageable);
        }
        return items;
    }

    @Override
    public Item findById(long id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.orElseThrow(() -> new ApplicationException(ITEM_NOT_FOUND, id));
    }

    @Override
    public List<Item> findAllByIds(List<Long> itemIds) {
        return checkNotNullAndNotEmpty(itemRepository.findAllById(itemIds), ITEMS_NOT_FOUND);
    }

    @Override
    public Page<Item> findPageByFilter(ItemTO itemTO, Pageable pageRequest) {
        Specification<Item> specification = byDepartment(itemTO.getDepartment())
                        .and(byPriceGreaterThanEqual(itemTO.getPrice()));
        Page<Item> itemPage = itemRepository.findAll(specification, pageRequest);
        return itemPage;
    }
}
