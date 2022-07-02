package com.epam.repairstuidiospring.controller;

import com.epam.repairstuidiospring.controller.dto.OrderDto;
import com.epam.repairstuidiospring.controller.dto.UserDto;
import com.epam.repairstuidiospring.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/orders")
    public List<OrderDto> getAllOrders() {
        return orderService.getOrders();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/order/{id}")
    public OrderDto getUser(@PathVariable int id) {
        return orderService.getOrderInfo(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/order")
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        log.info("Order to add->" + orderDto.getRequestId());
        return orderService.createOrder(orderDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/order/{id}")
    public OrderDto updateOrder(@PathVariable int id, @RequestBody OrderDto orderDto) {
        return orderService.updateOrder(id, orderDto);
    }

}
