package com.epam.repairstudio.api;


import com.epam.repairstudio.dto.OrderDto;
import com.epam.repairstudio.dto.group.OnCreate;
import com.epam.repairstudio.dto.group.OnUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Order management API")
@RequestMapping("/repair-studio/order")
public interface OrderApi {

    @ApiOperation("get all orders")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/")
    Page<OrderDto> getAll(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber,
                          @RequestParam("sortType") String sortType);

    @ApiOperation("create order")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/")
    OrderDto createOrder(@RequestBody @Validated(OnCreate.class) OrderDto orderDto);

    @ApiOperation("update order")
    @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "user id")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = "/{id}")
    OrderDto updateOrder(@PathVariable Long id, @RequestBody @Validated(OnUpdate.class) OrderDto orderDto);

    @ApiOperation("Delete order")
    @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Order id")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable long id);

    @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Order id")
    @ApiOperation("Get Order")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    OrderDto getOrder(@PathVariable Long id);

}
