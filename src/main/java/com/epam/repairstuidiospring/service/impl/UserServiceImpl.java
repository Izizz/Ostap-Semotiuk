package com.epam.repairstuidiospring.service.impl;

import com.epam.repairstuidiospring.controller.dto.UserDto;
import com.epam.repairstuidiospring.service.UserService;
import com.epam.repairstuidiospring.service.mapper.UserMapper;
import com.epam.repairstuidiospring.service.model.User;
import com.epam.repairstuidiospring.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getUser(String email) {
        log.info("getUser by email {}", email);
        User user = userRepository.getUser(email);
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    @Override
    public List<UserDto> listUsers() {
        log.info("get all users");
        return userRepository.listUsers()
                .stream()
                .map(this::mapUserToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("createUser with email {}", userDto.getEmail());
        User user = UserMapper.INSTANCE.mapUserDtoToUser(userDto);
        user = userRepository.createUser(user);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto updateUser(String email, UserDto userDto) {
        log.info("updateUser with email {}", email);
        User user = UserMapper.INSTANCE.mapUserDtoToUser(userDto);
        user = userRepository.updateUser(email, user);
        return mapUserToUserDto(user);
    }

    @Override
    public void deleteUser(String email) {
        log.info("deleteUser with email {}", email);
        userRepository.deleteUser(email);
    }

    private UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .phone(user.getPhone())
                .password(user.getPassword())
                .balance(user.getBalance())
                .email(user.getEmail())
                .build();
    }

}
