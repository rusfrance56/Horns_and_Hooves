package com.rest_jpa.repository;

import com.rest_jpa.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT i FROM Item i WHERE lower(i.name) like lower(concat('%', :filter, '%')) " +
            "OR lower(i.description) like lower(concat('%', :filter, '%'))")
    Page<Item> findPageByNameOrDescription(String filter, Pageable pageable);
}
