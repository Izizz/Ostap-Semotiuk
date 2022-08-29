package com.epam.repairstudio.mapper;


import com.epam.repairstudio.dto.UserDto;
import com.epam.repairstudio.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapUserToUserDto(User user);

    User mapUserDtoToUser(UserDto userDto);

    @Mapping(target = "id", ignore = true)
    User updateUser(@MappingTarget User oldUser, User updateUser);
}