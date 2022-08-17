package com.epam.repairstudio.controller;


import com.epam.repairstudio.api.OrderApi;
import com.epam.repairstudio.dto.OrderDto;
import com.epam.repairstudio.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController implements OrderApi {
    private final OrderService orderService;

    @Override
    public Page<OrderDto> getAll(Pageable pageable) {
        return orderService.getAll(pageable);
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        log.info("Order to add->" + orderDto.getRequestid());
        return orderService.create(orderDto);
    }

    @Override
    public OrderDto updateOrder(Long id, OrderDto orderDto) {
        return orderService.updateById(id, orderDto);
    }

}