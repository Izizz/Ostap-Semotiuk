package com.epam.repairstuidiospring.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@Builder
public class UserDto {
    @JsonProperty(access = READ_ONLY)
    private int id;

    private String firstName;
    private String lastName ;
    @Email
    private String email;
    private String role;
    @Pattern(regexp = "^[+0-9]{10,13}" )
    private String phone;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\S])[A-Za-z0-9\\S]{8,20}$]",
            message = "password should only contain at least 1 big letter, 1 numbers and at least 8 letters ")
    private String password;
    private double balance;
}
