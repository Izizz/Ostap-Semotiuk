package com.epam.repairstudio.controller;

import com.epam.repairstudio.config.TestConfig;
import com.epam.repairstudio.dto.OrderDto;
import com.epam.repairstudio.service.OrderService;
import com.epam.repairstudio.util.OrderTestDataUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.epam.repairstudio.util.OrderTestDataUtil.REQUESTID;
import static com.epam.repairstudio.util.UserTestDataUtil.ID;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
@ExtendWith(SpringExtension.class)
@Import(TestConfig.class)
public class OrderControllerTest {
    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getOrderTest() throws Exception {
        OrderDto orderDto = OrderTestDataUtil.createOrderDto();

        when(orderService.getById(REQUESTID)).thenReturn(orderDto);

        mockMvc.perform(get("/repair-studio/order/" + REQUESTID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.requestid").value(REQUESTID));
    }

    @Test
    void deleteOrderTest() throws Exception {
        doNothing().when(orderService).deleteById(REQUESTID);
        mockMvc.perform(delete("/repair-studio/order/" + REQUESTID));
    }

    @Test
    void getAllOrders() throws Exception {
        OrderDto orderDto1 = OrderTestDataUtil.createOrderDto();

        OrderDto orderDto2 = OrderTestDataUtil.createOrderDto();
        orderDto2.setRequestid(2L);

        OrderDto orderDto3 = OrderTestDataUtil.createOrderDto();
        orderDto3.setRequestid(3L);

        List<OrderDto> orders = Arrays.asList(orderDto1, orderDto2, orderDto3);

        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.DEFAULT_DIRECTION, "requestid"));

        when(orderService.getAll(eq(pageable))).thenReturn(new PageImpl<>(orders.subList(0, 2)));

        mockMvc
                .perform(
                        get("/repair-studio/order/?pageNumber=0&pageSize=1&sortType=requestid"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].requestid").value(REQUESTID));
        verify(orderService, times(1)).getAll(pageable);

    }

    @Test
    void createOrderTest() throws Exception {
        OrderDto orderDto = OrderTestDataUtil.createOrderDto();
        when(orderService.create(any(OrderDto.class))).thenReturn(orderDto);

        mockMvc.perform(post("/repair-studio/order/")
                        .content(objectMapper.writeValueAsString(orderDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.requestid").value(REQUESTID));

    }

    @Test
    void updateOrderTest() throws Exception {
        OrderDto orderDto = OrderTestDataUtil.createOrderDtoUpdate();

        when(orderService.updateById(eq(ID), any(OrderDto.class))).thenReturn(orderDto);

        mockMvc.perform(patch("/repair-studio/order/" + REQUESTID)
                        .content(objectMapper.writeValueAsString(orderDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.requestid").value(REQUESTID));

    }
}
