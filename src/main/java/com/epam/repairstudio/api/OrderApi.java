package com.epam.repairstudio.api;


import com.epam.repairstudio.dto.OrderDto;
import com.epam.repairstudio.dto.group.OnCreate;
import com.epam.repairstudio.dto.group.OnUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<OrderDto> getAll(Pageable pageable);

//    @ApiOperation("get user order")
//    @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "user id")
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping(value = "/{id}")
//    OrderDto getUserOrder(@PathVariable int id);

    @ApiOperation("create order")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    OrderDto createOrder(@RequestBody @Validated(OnCreate.class) OrderDto orderDto);

    @ApiOperation("update order")
    @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "user id")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}")
    OrderDto updateOrder(@PathVariable Long id, @RequestBody @Validated(OnUpdate.class) OrderDto orderDto);

}
