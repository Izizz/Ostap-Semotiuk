package com.epam.repairstuidiospring.service.impl;

import com.epam.repairstuidiospring.controller.dto.OrderDto;
import com.epam.repairstuidiospring.controller.dto.UserDto;
import com.epam.repairstuidiospring.service.OrderService;
import com.epam.repairstuidiospring.service.model.Order;
import com.epam.repairstuidiospring.service.model.User;
import com.epam.repairstuidiospring.service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public OrderDto getOrderInfo(int orderId) {
        log.info("getOrderInfo by ID {}",orderId);
        Order order  = orderRepository.getOrderInfo(orderId);
        return mapOrderToOrderDto(order);
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        log.info("Create order with ID {}",orderDto.getRequestId());
        Order order = mapOrderDtoToOrder(orderDto);
        order = orderRepository.createOrder(order);
        return mapOrderToOrderDto(order);
    }

    @Override
    public List<OrderDto> getUserOrders(int userId) {
        log.info("GetOrder by user ID {}",userId );
        List<Order> list = orderRepository.getUserOrders(userId);
        return list.stream().map(this::mapOrderToOrderDto).toList();
    }

    @Override
    public OrderDto updateOrder(int orderId , OrderDto orderDto) {
        log.info("update order with ID {}", orderId);
        Order order =mapOrderDtoToOrder(orderDto);
        order = orderRepository.updateOrder(orderId,order);
        return mapOrderToOrderDto(order);
    }

    @Override
    public List<OrderDto> getOrders() {
        log.info("Get all orders");
        return orderRepository.getOrders().stream().map(this::mapOrderToOrderDto).toList();
    }

    private OrderDto mapOrderToOrderDto(Order order) {
        return OrderDto.builder()
                .requestId(order.getRequestId())
                .user_id(order.getUser_id())
                .requestDescr(order.getRequestDescr())
                .master(order.getMaster())
                .price(order.getPrice())
                .feedback(order.getFeedback())
                .status(order.getStatus())
                .build();
    }

    private Order mapOrderDtoToOrder(OrderDto orderDto) {
        return Order.builder()
                .requestId(orderDto.getRequestId())
                .user_id(orderDto.getUser_id())
                .requestDescr(orderDto.getRequestDescr())
                .master(orderDto.getMaster())
                .price(orderDto.getPrice())
                .feedback(orderDto.getFeedback())
                .status(orderDto.getStatus())
                .build();
    }

}
