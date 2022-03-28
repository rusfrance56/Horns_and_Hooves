package com.rest_jpa.repository;

import com.rest_jpa.entity.CustomerOrder;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    @Override
    @EntityGraph(attributePaths = {"items", "user"})
    List<CustomerOrder> findAll();

    List<CustomerOrder> findByItems_Id(long id);
}
