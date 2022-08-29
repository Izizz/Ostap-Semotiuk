package com.epam.repairstudio.serviceImpl;

import com.epam.repairstudio.model.Order;
import com.epam.repairstudio.util.OrderTestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImpl {
    @Test
    void getByIdTest(){
        Order order = OrderTestDataUtil.createOrder();
        when()
    }
}
