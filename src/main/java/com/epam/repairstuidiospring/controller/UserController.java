package com.epam.repairstuidiospring.controller;

import com.epam.repairstuidiospring.controller.api.UserApi;
import com.epam.repairstuidiospring.controller.dto.UserDto;
import com.epam.repairstuidiospring.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController implements UserApi {


    private final UserService userService;

    @Override
    public List<UserDto> getAllUsers() {
        return userService.listUsers();
    }


    @Override
    public UserDto getUser(String email) {
        return userService.getUser(email);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("User to add->" + userDto.getEmail());
        return userService.createUser(userDto);
    }

    @Override
    public UserDto updateUser(String email, UserDto userDto) {
        return userService.updateUser(email, userDto);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String email) {
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }

}
