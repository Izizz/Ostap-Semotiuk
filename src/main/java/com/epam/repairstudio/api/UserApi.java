package com.epam.repairstudio.api;


import com.epam.repairstudio.dto.UserDto;
import com.epam.repairstudio.dto.group.OnCreate;
import com.epam.repairstudio.dto.group.OnUpdate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Api(tags = "User management API")
@RequestMapping("/repair-studio/user")
public interface UserApi {

    @ApiImplicitParam(name = "email", paramType = "path", required = true, value = "User email")
    @ApiOperation("Get User")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{email}")
    UserDto getUser(@PathVariable String email);

    @ApiImplicitParam(name = "role", paramType = "path", required = true, value = "User role")
    @ApiOperation("Get User by Role")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "role/{role}")
    UserDto getByRole(@PathVariable String role);

    @ApiOperation("Create user")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping
    UserDto createUser(@Validated(OnCreate.class) @RequestBody UserDto userDto);

    @ApiOperation("Get all users")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/")
    Page<UserDto> getAll(
            @RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber,
            @RequestParam("sortType") String sortType);

    @ApiOperation("Delete user")
    @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "User id")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable long id);

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", paramType = "path", required = true, value = "Entity id")
    })
    @ApiOperation("Update entity by Id")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    @ResponseBody
    UserDto updateById(@PathVariable long id, @RequestBody @Validated(OnUpdate.class) UserDto userDto);


}
