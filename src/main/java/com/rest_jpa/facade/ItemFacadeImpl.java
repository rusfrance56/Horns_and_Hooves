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
        return to;
//        throw new ApplicationException(ErrorKey.TASK_NOT_FOUND, 1);
    }

    @Override
    public void update(ItemTO to) {
        checkNotNull(to.getId(), WRONG_INPUT_DATA, "id");
        Item item = itemService.findById(to.getId());
        List<String> prevImageUrl = item.getImageUrls();
        Item.updateEntityFromTO(item, to);
        itemService.update(item);
        List<String> removedImages = prevImageUrl.stream()
                .filter(img -> !to.getImageUrls().contains(img))
                .collect(Collectors.toList());
        //        todo delete old images which don't exist in new list from to
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
}
