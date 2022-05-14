package com.rest_jpa.facade;

import com.rest_jpa.entity.to.ItemTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemFacade {
    ItemTO create(ItemTO to);
    void update(ItemTO to);
    void delete(long id);
    List<ItemTO> findAll();
    Page<ItemTO> findPageByFilter(String filter, Pageable pageable);
    ItemTO findById(long id);
}
