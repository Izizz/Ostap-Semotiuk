package com.epam.repairstudio.service.impl;


import com.epam.repairstudio.dto.OrderDto;
import com.epam.repairstudio.mapper.OrderMapper;
import com.epam.repairstudio.model.Order;
import com.epam.repairstudio.repository.OrderRepository;
import com.epam.repairstudio.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderDto getById(Long orderId) {
        log.info("getOrderInfo by ID {}", orderId);
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new EntityNotFoundException("Order not found with id " + orderId));
        return OrderMapper.INSTANCE.mapOrderToOrderDto(order);
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        log.info("Create order with ID {}", orderDto.getRequestid());
        Order order = OrderMapper.INSTANCE.mapOrderDtoToOrder(orderDto);
        order = orderRepository.save(order);
        return OrderMapper.INSTANCE.mapOrderToOrderDto(order);
    }


    @Override
    public OrderDto updateById(Long orderId, OrderDto orderDto) {
        log.info("update order with ID {}", orderId);
        if(!orderRepository.existsById(orderId)){
            throw  new EntityNotFoundException("Order not found with id " + orderId);
        }
        Order order = OrderMapper.INSTANCE.mapOrderDtoToOrder(orderDto);
        order = orderRepository.save(order);
        return OrderMapper.INSTANCE.mapOrderToOrderDto(order);
    }

    @Override
    public Page<OrderDto> getAll(Pageable pageable) {
        log.info("Get all orders");
    return orderRepository.findAll(pageable).map(OrderMapper.INSTANCE::mapOrderToOrderDto);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting order with id " + id );
        orderRepository.deleteById(id);

    }

}
