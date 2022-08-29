package com.epam.repairstudio.service;


import com.epam.repairstudio.dto.UserDto;

public interface UserService extends CrudService<UserDto, Long> {

    UserDto getByEmail(String email);

    boolean existsByEmail(String email);

    UserDto getByRole(String role);

    void deleteUser(String email);

}
