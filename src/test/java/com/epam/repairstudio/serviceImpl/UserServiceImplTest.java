package com.epam.repairstudio.serviceImpl;


import com.epam.repairstudio.dto.UserDto;
import com.epam.repairstudio.exception.UserException;
import com.epam.repairstudio.mapper.UserMapper;
import com.epam.repairstudio.model.User;
import com.epam.repairstudio.repository.UserRepository;
import com.epam.repairstudio.service.impl.UserServiceImpl;
import com.epam.repairstudio.util.UserTestDataUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.epam.repairstudio.util.UserTestDataUtil.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void getAllTest() {
        UserDto userDto1 = UserTestDataUtil.createUserDto();

        UserDto userDto2 = UserTestDataUtil.createUserDto();
        userDto2.setId(2L);

        UserDto userDto3 = UserTestDataUtil.createUserDto();
        userDto3.setId(3L);

        List<User> users =
                Arrays.asList(
                        UserMapper.INSTANCE.mapUserDtoToUser(userDto1),
                        UserMapper.INSTANCE.mapUserDtoToUser(userDto2),
                        UserMapper.INSTANCE.mapUserDtoToUser(userDto3));

        Pageable pageable = Pageable.unpaged();

        when(userRepository.findAll(pageable))
                .thenReturn(new PageImpl<>(users, pageable, users.size()));

        Page<UserDto> resultPageableUsers = userService.getAll(pageable);

        assertThat(resultPageableUsers.getContent(), hasItems(userDto1, userDto2, userDto3));

        verify(userRepository, times(1)).findAll(pageable);
    }

    @Test
    void getUserTest() {
        User user = UserTestDataUtil.createUser();
        when(userRepository.findById(ID)).thenReturn(Optional.of(user));

        UserDto userDto = userService.getById(user.getId());

        assertThat(userDto, allOf(
                hasProperty("email", equalTo(user.getEmail())),
                hasProperty("firstname", equalTo(user.getFirstname())),
                hasProperty("lastName", equalTo(user.getLastName())),
                hasProperty("phone", equalTo(user.getPhone())),
                hasProperty("password", equalTo(user.getPassword()))

        ));
    }

    @Test
    void getUserUserNotFound() {
        when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());
        assertThrows(UserException.class, () -> userService.getByEmail(EMAIL));
    }

    @Test
    void createUserTest() {
        User testUser = UserTestDataUtil.createUser();
        UserDto testUserDto = UserTestDataUtil.createUserDto();
        when(userRepository.save(any())).thenReturn(testUser);

        UserDto userDto = userService.create(testUserDto);

        assertThat(userDto, allOf(
                hasProperty("firstname", equalTo(testUser.getFirstname())),
                hasProperty("lastName", equalTo(testUser.getLastName())),
                hasProperty("email", equalTo(testUser.getEmail())),
                hasProperty("phone", equalTo(testUser.getPhone())),
                hasProperty("password", equalTo(testUser.getPassword()))

        ));
    }

    @Test
    void createUserUserAlreadyExistsTest() {
        UserDto userDto = UserTestDataUtil.createUserDto();
        when(userRepository.existsByEmail(EMAIL)).thenReturn(true);
        assertThrows(EntityExistsException.class, () -> userService.create(userDto));
    }

    @Test
    void updateUserTest() {
        UserDto testUserDto = UserTestDataUtil.createUserDto();
        User testUser = UserTestDataUtil.createUser();
        when(userRepository.findById(testUserDto.getId())).thenReturn(Optional.of(testUser));
        when(userRepository.save(any())).thenReturn(testUser);
        UserDto userDto = userService.updateById(testUser.getId(), testUserDto);
        assertThat(userDto, allOf(
                hasProperty("firstname", equalTo(testUserDto.getFirstname())),
                hasProperty("lastName", equalTo(testUserDto.getLastName())),
                hasProperty("email", equalTo(testUserDto.getEmail()))

        ));
    }

    @Test
    void deleteUser() {
        User testUser = UserTestDataUtil.createUser();
        when(userRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.of(testUser));

        userService.deleteUser(testUser.getEmail());

        verify(userRepository, times(1)).delete(testUser);
    }

    @Test
    void deleteUserById() {
        doNothing().when(userRepository).deleteById(ID);

        userService.deleteById(ID);

        verify(userRepository, times(1)).deleteById(ID);


    }

    @Test
    void deleteUserUserNotFound() {
        when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(EMAIL));
    }


    @Test
    void getUserByRole() {
        User user = UserTestDataUtil.createUser();
        when(userRepository.findByRole(ROLE)).thenReturn(Optional.of(user));

        UserDto userDto = userService.getByRole(user.getRole());

        assertThat(userDto, allOf(
                hasProperty("email", equalTo(user.getEmail())),
                hasProperty("firstname", equalTo(user.getFirstname())),
                hasProperty("lastName", equalTo(user.getLastName())),
                hasProperty("phone", equalTo(user.getPhone())),
                hasProperty("password", equalTo(user.getPassword())),
                hasProperty("role", equalTo(user.getRole()))

        ));
    }
}
