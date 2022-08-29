package com.epam.repairstudio.service.impl;


import com.epam.repairstudio.dto.UserDto;
import com.epam.repairstudio.exception.UserException;
import com.epam.repairstudio.mapper.UserMapper;
import com.epam.repairstudio.model.User;
import com.epam.repairstudio.repository.UserRepository;
import com.epam.repairstudio.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getByEmail(String email) {
        log.info("getUser by email {}", email);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserException(
                "User not found with email " + email));
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }


    @Override
    public boolean existsByEmail(String email) {
        log.info("Checking if exists by email");
        return userRepository.existsByEmail(email);
    }


    @Override
    public UserDto getById(Long id) {
        log.info("Getting user with id {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + id));
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        log.info("Getting all users");
        return userRepository.findAll(pageable).map(UserMapper.INSTANCE::mapUserToUserDto);
    }

    @Override
    public UserDto create(UserDto dto) {
        log.info("Creating user with id {}", dto.getId());
        User user = UserMapper.INSTANCE.mapUserDtoToUser(dto);
        if (!existsByEmail(dto.getEmail())) {
            user = userRepository.save(user);
        } else {
            throw new EntityExistsException();
        }
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }


    @Override
    public UserDto updateById(Long id, UserDto dto) {
        log.info("Updating user with id {}", dto.getId());
        User persistedUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "User not found with id " + id));
        User updatedUser = UserMapper.INSTANCE.updateUser(persistedUser, UserMapper.INSTANCE.mapUserDtoToUser(dto));
        User oldUser = userRepository.save(updatedUser);
        return UserMapper.INSTANCE.mapUserToUserDto(oldUser);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting user with id " + id);
        userRepository.deleteById(id);
    }


    @Override
    public void deleteUser(String email) {
        log.info("Deleting user with email " + email);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public UserDto getByRole(String role) {
        User user = userRepository.findByRole(role)
                .orElseThrow(() -> new EntityNotFoundException("User not found with role " + role));
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }
}

