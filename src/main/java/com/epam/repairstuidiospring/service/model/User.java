package com.epam.repairstuidiospring.service.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @JsonProperty(access = READ_ONLY)
    @Id
    private long id;

    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String role;
    @Pattern(regexp = "^[+0-9]{10,13}")
    private String phone;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\S])[A-Za-z0-9\\S]{8,20}$]",
            message = "password should only contain at least 1 big letter, 1 numbers and at least 8 letters ")
    private String password;
    private double balance;

}

