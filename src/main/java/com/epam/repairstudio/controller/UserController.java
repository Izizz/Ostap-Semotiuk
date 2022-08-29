package com.epam.repairstudio.controller;

import com.epam.repairstudio.api.UserApi;
import com.epam.repairstudio.dto.UserDto;
import com.epam.repairstudio.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public Page<UserDto> getAll(@RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber,
                                @RequestParam("sortType") String sortType) {
        log.info("Getting all users");
        return userService.getAll(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.DEFAULT_DIRECTION, sortType)));
    }


    @Override
    public UserDto getUser(String email) {
        return userService.getByEmail(email);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("User to add->" + userDto.getEmail());
        return userService.create(userDto);
    }

    @Override
    public UserDto updateById(long id, UserDto userDto) {
        return userService.updateById(id, userDto);
    }


    @Override
    public UserDto getByRole(String role) {
        return userService.getByRole(role);
    }

    @Override
    public ResponseEntity<Void> deleteById(long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
