package com.rest_jpa.facade;

import com.rest_jpa.entity.Item;
import com.rest_jpa.entity.to.ItemTO;
import com.rest_jpa.fileUploading.StorageService;
import com.rest_jpa.servise.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rest_jpa.exceptions.ApplicationException.checkNotNull;
import static com.rest_jpa.exceptions.ErrorKey.WRONG_INPUT_DATA;

@Service
@AllArgsConstructor
public class ItemFacadeImpl implements ItemFacade {

    private ItemService itemService;
    private StorageService storageService;

    @Override
    public ItemTO create(ItemTO to) {
        Item item = new Item(to);
        to.setId(itemService.create(item).getId());
        updateImages(item, to);
        return to;
    }

    @Override
    public void update(ItemTO to) {
        checkNotNull(to.getId(), WRONG_INPUT_DATA, "id");
        Item item = itemService.findById(to.getId());
        updateImages(item, to);
        Item.updateEntityFromTO(item, to);
        itemService.update(item);
    }

    private void updateImages(Item item, ItemTO to) {
        List<String> prevImageNames = item.getImageNames();
        List<String> removedImages = prevImageNames.stream()
                .filter(img -> !to.getImageNames().contains(img))
                .collect(Collectors.toList());
        storageService.deleteAll(removedImages);
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
    public Page<ItemTO> findPageByFilter(String filter, Pageable pageable) {
        Page<Item> itemsPage = itemService.findPageByFilter(filter, pageable);
        List<ItemTO> itemTOResponse = itemsPage.getContent().stream().map(ItemTO::new).collect(Collectors.toList());
        return new PageImpl<>(itemTOResponse, itemsPage.getPageable(), itemsPage.getTotalElements());
    }

    @Override
    public ItemTO findById(long id) {
        Item item = itemService.findById(id);
        return new ItemTO(item);
    }

    @Override
    public Page<ItemTO> findPageByFilter(ItemTO itemTO, Pageable pageable) {
        Page<Item> itemsPage = itemService.findPageByFilter(itemTO, pageable);
        List<ItemTO> itemTOResponse = itemsPage.getContent().stream().map(ItemTO::new).collect(Collectors.toList());
        return new PageImpl<>(itemTOResponse, itemsPage.getPageable(), itemsPage.getTotalElements());
    }
}
