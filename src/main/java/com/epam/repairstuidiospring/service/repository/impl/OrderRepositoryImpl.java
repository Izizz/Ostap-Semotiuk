package com.epam.repairstuidiospring.service.repository.impl;

import com.epam.repairstuidiospring.service.model.Order;
import com.epam.repairstuidiospring.service.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private final List<Order> list = new ArrayList<>();

    @Override
    public Order getOrderInfo(int orderId) {
        return list.stream()
                .filter(order -> ((String.valueOf(order.getRequestId()))
                        .equals(String.valueOf(orderId))))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Order createOrder(Order order) {
        list.add(order);
        return order;
    }

    @Override
    public List<Order> getUserOrders(int userId) {
        List<Order> userOrders = new ArrayList<>();
        list.stream()
                .filter(order -> String.valueOf(order.getUser_id()).equals(String.valueOf(userId)))
                .forEach(userOrders::add);
        return userOrders;
    }

    @Override
    public Order updateOrder(int OrderId, Order order) {
        boolean isDeleted = list.removeIf(o -> String.valueOf(o.getRequestId()).equals(String.valueOf(OrderId)));
        if (isDeleted) {
            list.add(order);
        } else {
            throw new RuntimeException("Order is not found!");
        }
        return order;

    }

    @Override
    public List<Order> getOrders() {
        return new ArrayList<>(list);
    }
}
