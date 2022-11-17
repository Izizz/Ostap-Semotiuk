package com.epam.repairstuidiospring.service.repository;

import com.epam.repairstuidiospring.service.model.Order;

import java.util.List;

public interface OrderRepository {
    Order getOrderInfo(int orderId);

    Order createOrder(Order order);

    List<Order> getUserOrders(int userId);

    Order updateOrder(int OrderId, Order order);

    List<Order> getOrders();

}
