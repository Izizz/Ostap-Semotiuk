package com.epam.repairstuidiospring.service.mapper;

import com.epam.repairstuidiospring.controller.dto.OrderDto;
import com.epam.repairstuidiospring.service.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto mapOrderToOrderDto(Order order);

    Order mapOrderDtoToOrder(OrderDto orderDto);

}
