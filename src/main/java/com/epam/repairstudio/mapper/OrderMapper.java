package com.epam.repairstudio.mapper;

import com.epam.repairstudio.dto.OrderDto;
import com.epam.repairstudio.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDto mapOrderToOrderDto(Order order);

    Order mapOrderDtoToOrder(OrderDto orderDto);

    @Mapping(target = "requestid", ignore = true)
    Order updateOrder(@MappingTarget Order oldOrder, Order updateOrder);

}
