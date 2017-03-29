package com.rest_jpa.repository;

import com.rest_jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByDepartmentName(String department);
    List<Order> findAllByEmployeeId(long id);



    /*@Query("select ord from Order ord where ord.date > current_timestamp")
    List<Order> findAllUnfinished();*/
}
