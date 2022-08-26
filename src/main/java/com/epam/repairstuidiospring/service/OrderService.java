package com.epam.repairstuidiospring.service;

import com.epam.repairstuidiospring.controller.dto.OrderDto;
import com.epam.repairstuidiospring.service.model.Order;

import java.util.List;

public interface OrderService {
    OrderDto getOrderInfo(int orderId);

    OrderDto createOrder(OrderDto orderDto);

    List<OrderDto> getUserOrders(int userId);

    OrderDto updateOrder(int orderId,OrderDto orderDto);

    List<OrderDto> getOrders();

}
