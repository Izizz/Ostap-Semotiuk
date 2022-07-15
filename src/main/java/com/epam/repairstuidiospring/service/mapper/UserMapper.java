package com.epam.repairstuidiospring.service.mapper;


import com.epam.repairstuidiospring.controller.dto.UserDto;
import com.epam.repairstuidiospring.service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapUserToUserDto(User user);

    User mapUserDtoToUser(UserDto userDto);


}
