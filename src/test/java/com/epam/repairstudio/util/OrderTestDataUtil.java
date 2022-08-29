package com.epam.repairstudio.util;

import com.epam.repairstudio.dto.OrderDto;
import com.epam.repairstudio.model.Order;
import com.epam.repairstudio.model.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OrderTestDataUtil {

    public static final Long REQUESTID = 1l;
    public static final User USER = UserTestDataUtil.createUser();
    public static final String REQUEST_DESCR = "request descr";
    public static final double PRICE = 100.0;
    public static final String STATUS = "Pending to approve";

    public static Order createOrder() {
        return Order.builder()
                .requestid(REQUESTID)
                .user(USER)
                .requestDescr(REQUEST_DESCR)
                .price(PRICE)
                .status(STATUS).build();
    }

    public static OrderDto createOrderDto() {
        return OrderDto.builder()
                .requestid(REQUESTID)
                .user(USER)
                .requestDescr(REQUEST_DESCR)
                .price(PRICE)
                .status(STATUS).build();
    }

    public static OrderDto createOrderDtoUpdate() {
        return OrderDto.builder()
                .requestid(REQUESTID)
                .price(PRICE)
                .status(STATUS).build();
    }

}
