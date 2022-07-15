package com.epam.repairstuidiospring.controller;

import com.epam.repairstuidiospring.controller.api.OrderApi;
import com.epam.repairstuidiospring.controller.dto.OrderDto;
import com.epam.repairstuidiospring.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController implements OrderApi {
    private final OrderService orderService;

    @Override
    public List<OrderDto> getAllOrders() {
        return orderService.getOrders();
    }
    @Override
    public OrderDto getUserOrder( int id) {
        return orderService.getOrderInfo(id);
    }
    @Override
    public OrderDto createOrder( OrderDto orderDto) {
        log.info("Order to add->" + orderDto.getRequestId());
        return orderService.createOrder(orderDto);
    }
    @Override
    public OrderDto updateOrder( int id, OrderDto orderDto) {
        return orderService.updateOrder(id, orderDto);
    }

}
