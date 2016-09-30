package com.rest_jpa.repository;

import com.rest_jpa.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by User on 26.09.2016.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select ord from Order ord join ord.department depart where depart.name = :dep")
    List<Order> findAllByDepartmentName(@Param("dep") String department);

    @Query("select ord from Order ord join ord.employee emp where emp.id = :emp_id")
    List<Order> findAllByEmployeeId(@Param("emp_id") long id);
}
