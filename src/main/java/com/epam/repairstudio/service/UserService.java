package com.epam.repairstudio.service;


import com.epam.repairstudio.dto.UserDto;

public interface UserService extends CrudService<UserDto, Long>{

    UserDto getByEmail(String email);

    UserDto topUpBalance (long id , double balance);

    boolean existsByEmail(String email);

    UserDto getByRole(String role);

}
