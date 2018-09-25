package com.rest_jpa.repository;

import com.rest_jpa.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    //    List<CustomerOrder> findAllByDepartmentName(String department);
//    List<CustomerOrder> findAllById(long id);

    List<CustomerOrder> findByItems_Id(long id);
}
