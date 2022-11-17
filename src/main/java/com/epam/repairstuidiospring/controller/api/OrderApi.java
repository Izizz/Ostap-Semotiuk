package com.epam.repairstuidiospring.controller.api;

import com.epam.repairstuidiospring.controller.dto.OrderDto;
import com.epam.repairstuidiospring.controller.dto.group.OnCreate;
import com.epam.repairstuidiospring.controller.dto.group.OnUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Order management API")
@RequestMapping("/repair-studio/order")
public interface OrderApi {

    @ApiOperation("get all orders")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/")
    List<OrderDto> getAllOrders();

    @ApiOperation("get user order")
    @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "user id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    OrderDto getUserOrder(@PathVariable int id);

    @ApiOperation("create order")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    OrderDto createOrder(@RequestBody @Validated(OnCreate.class) OrderDto orderDto);

    @ApiOperation("update order")
    @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "user id")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    OrderDto updateOrder(@PathVariable int id, @RequestBody @Validated(OnUpdate.class) OrderDto orderDto);

}
