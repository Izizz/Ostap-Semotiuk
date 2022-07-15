package com.epam.repairstuidiospring.service;

import com.epam.repairstuidiospring.controller.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUser(String email);

    List<UserDto> listUsers();

    UserDto createUser(UserDto userDto);

    UserDto updateUser(String email, UserDto userDto);

    void deleteUser(String email);

}
