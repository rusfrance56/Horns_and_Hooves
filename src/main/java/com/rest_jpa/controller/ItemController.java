package com.rest_jpa.controller;

import com.rest_jpa.entity.to.ItemTO;
import com.rest_jpa.facade.ItemFacade;
import lombok.AllArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@CrossOrigin
@AllArgsConstructor
public class ItemController {

    private ItemFacade itemFacade;

    @PostMapping
    public ResponseEntity<ItemTO> create(@RequestBody ItemTO to) {
        return new ResponseEntity<>(itemFacade.create(to), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody ItemTO to) {
        itemFacade.update(to);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        itemFacade.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ItemTO>> findAll() {
        return new ResponseEntity<>(itemFacade.findAll(), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<ItemTO>> findAllWithPagination(@RequestParam int page,
                                                              @RequestParam int size,
                                                              @RequestParam String sort,
                                                              @RequestParam String dir,
                                                              @RequestParam(required = false) String filter) {
        Pageable pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(dir), sort));
        String encodedFilter = StringEscapeUtils.unescapeHtml4(filter);
        Page<ItemTO> itemsTOPage = itemFacade.findPageByFilter(encodedFilter, pageRequest);
        return new ResponseEntity<>(itemsTOPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemTO> findById(@PathVariable("id") long id) {
        return new ResponseEntity<>(itemFacade.findById(id), HttpStatus.OK);
    }
}
