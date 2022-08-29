package com.epam.repairstudio.service;

import com.epam.repairstudio.dto.OrderDto;


public interface OrderService extends CrudService<OrderDto, Long> {

    OrderDto getById(Long orderId);

}
