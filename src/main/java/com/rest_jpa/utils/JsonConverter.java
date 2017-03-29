package com.rest_jpa.utils;

import com.rest_jpa.entity.Order;
import com.rest_jpa.entity.response.OrderResponse;

import java.util.ArrayList;
import java.util.List;

public class JsonConverter {

    public static OrderResponse convert(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setName(order.getName());
        orderResponse.setDateTime(order.getDate());
        orderResponse.setDepartment(order.getDepartment().getName());
        orderResponse.setEmployee(order.getEmployee().getName().concat(" ")
                .concat(order.getEmployee().getSurName()));
        return orderResponse;
    }

    public static List<OrderResponse> convert(List<Order> orders) {
        List<OrderResponse> responseList = new ArrayList<>();
        for (Order order : orders) {
            OrderResponse orderResponse = convert(order);
            responseList.add(orderResponse);
        }
        return responseList;
    }
}
