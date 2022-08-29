package com.epam.repairstudio.controller;


import com.epam.repairstudio.api.OrderApi;
import com.epam.repairstudio.dto.OrderDto;
import com.epam.repairstudio.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class OrderController implements OrderApi {

    private final OrderService orderService;

    @Override
    public Page<OrderDto> getAll(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber,
                                 @RequestParam("sortType") String sortType) {
        return orderService.getAll(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.DEFAULT_DIRECTION, sortType)));
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

    @Override
    public ResponseEntity<Void> deleteById(long id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    @Override
    public OrderDto getOrder(Long id) {
        return orderService.getById(id);
    }

}