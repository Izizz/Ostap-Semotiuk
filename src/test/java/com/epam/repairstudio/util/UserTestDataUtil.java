package com.epam.repairstudio.util;

import com.epam.repairstudio.dto.UserDto;
import com.epam.repairstudio.model.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TestDataUtil {
    public static final String FIRST_NAME = "firstname";
    public static final String LAST_NAME = "lastname";
    public static final String EMAIL = "email@email.com";
    public static final String PASSWORD = "Password123!";
    public static final String ROLE = "role";
    public static final String PHONE = "+380965443312";
    public static final double BALANCE = 0.00;
    public static final Long ID = 1L;

    public static User createUser() {
        return User.builder()
                .id(ID)
                .firstname(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .role(ROLE)
                .phone(PHONE)
                .balance(BALANCE)
                .build();
    }

    public static UserDto createUserDto() {
        return UserDto.builder()
                .id(ID)
                .firstname(FIRST_NAME)
                .lastName(LAST_NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .role(ROLE)
                .phone(PHONE)
                .balance(BALANCE)
                .build();
    }

    public static UserDto createUserDtoUpdate(){
        return UserDto.builder()
                .id(ID)
                .firstname(FIRST_NAME)
                .lastName(LAST_NAME)
                .role(ROLE)
                .balance(BALANCE)
                .build();
    }
}
