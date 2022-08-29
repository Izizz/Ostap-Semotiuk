package com.epam.repairstudio.serviceImpl;

import com.epam.repairstudio.dto.OrderDto;
import com.epam.repairstudio.mapper.OrderMapper;
import com.epam.repairstudio.model.Order;
import com.epam.repairstudio.repository.OrderRepository;
import com.epam.repairstudio.service.impl.OrderServiceImpl;
import com.epam.repairstudio.util.OrderTestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.epam.repairstudio.util.OrderTestDataUtil.REQUESTID;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void getByIdTest() {
        Order order = OrderTestDataUtil.createOrder();
        when(orderRepository.findById(REQUESTID)).thenReturn(Optional.of(order));

        OrderDto orderDto = orderService.getById(REQUESTID);

        assertThat(orderDto, allOf(
                hasProperty("requestid", equalTo(order.getRequestid())),
                hasProperty("requestDescr", equalTo(order.getRequestDescr())),
                hasProperty("user", equalTo(order.getUser())),
                hasProperty("price", equalTo(order.getPrice())),
                hasProperty("status", equalTo(order.getStatus()))
        ));
    }

    @Test
    void createOrderTest() {
        Order order = OrderTestDataUtil.createOrder();
        OrderDto orderDto = OrderTestDataUtil.createOrderDto();
        when(orderRepository.save(any())).thenReturn(order);
        OrderDto orderDto1 = orderService.create(orderDto);

        assertThat(orderDto1, allOf(
                hasProperty("requestid", equalTo(order.getRequestid())),
                hasProperty("requestDescr", equalTo(order.getRequestDescr())),
                hasProperty("user", equalTo(order.getUser())),
                hasProperty("price", equalTo(order.getPrice())),
                hasProperty("status", equalTo(order.getStatus()))
        ));
    }

    @Test
    void deleteOrderTest() {
        doNothing().when(orderRepository).deleteById(REQUESTID);
        orderService.deleteById(REQUESTID);
        verify(orderRepository, times(1)).deleteById(REQUESTID);
    }

    @Test
    void updateOrderTest() {
        OrderDto orderDto = OrderTestDataUtil.createOrderDto();
        Order order = OrderTestDataUtil.createOrder();
        when(orderRepository.findById(order.getRequestid())).thenReturn(Optional.of(order));
        when(orderRepository.save(any())).thenReturn(order);
        OrderDto orderDto1 = orderService.updateById(order.getRequestid(), orderDto);
        assertThat(orderDto1, allOf(
                hasProperty("price", equalTo(order.getPrice())),
                hasProperty("status", equalTo(order.getStatus())),
                hasProperty("user", equalTo(order.getUser()))
        ));
    }

    @Test
    void getAllOrdersTest() {
        OrderDto order1 = OrderTestDataUtil.createOrderDto();

        OrderDto order2 = OrderTestDataUtil.createOrderDto();
        order2.setRequestid(2l);

        OrderDto order3 = OrderTestDataUtil.createOrderDto();
        order3.setRequestid(3l);

        List<Order> orders =
                Arrays.asList(
                        OrderMapper.INSTANCE.mapOrderDtoToOrder(order1),
                        OrderMapper.INSTANCE.mapOrderDtoToOrder(order2),
                        OrderMapper.INSTANCE.mapOrderDtoToOrder(order3));

        Pageable pageable = Pageable.unpaged();

        when(orderRepository.findAll(pageable))
                .thenReturn(new PageImpl<>(orders, pageable, orders.size()));

        Page<OrderDto> resultPageableOrders = orderService.getAll(pageable);
        assertThat(resultPageableOrders.getContent(), hasItems(order1, order2, order3));
        verify(orderRepository, times(1)).findAll(pageable);

    }
}
