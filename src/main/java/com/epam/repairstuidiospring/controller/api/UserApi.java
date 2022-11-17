package com.epam.repairstuidiospring.controller.api;

import com.epam.repairstuidiospring.controller.dto.UserDto;
import com.epam.repairstuidiospring.controller.dto.group.OnCreate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "User management API")
@RequestMapping("/repair-studio/user")
public interface UserApi {

    @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User email")
    @ApiOperation("Get User")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "repair-studio/user/{email}")
    UserDto getUser(@PathVariable String email);

    @ApiOperation("Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    UserDto createUser(@Validated(OnCreate.class) @RequestBody UserDto userDto);


    @ApiOperation("Update user")
    @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User email")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{email}")
    UserDto updateUser(@PathVariable String email, @RequestBody UserDto userDto);

    @ApiOperation("Get all users")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    List<UserDto> getAllUsers();

    @ApiOperation("Delete user")
    @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User email")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{email}")
    ResponseEntity<Void> deleteUser(@PathVariable String email);
}
