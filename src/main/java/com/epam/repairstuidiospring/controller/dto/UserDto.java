package com.epam.repairstuidiospring.controller.dto;


import com.epam.repairstuidiospring.controller.dto.group.OnCreate;
import com.epam.repairstuidiospring.controller.dto.group.OnUpdate;
import com.epam.repairstuidiospring.service.validation.ContactnumberConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    @JsonProperty(access = READ_ONLY)
    private long id;

    @NotBlank(message = "Firstname is mandatory", groups = OnCreate.class)
    private String firstName;

    @NotBlank(message = "Lastname is mandatory", groups = OnCreate.class)
    private String lastName;

    @Email
    @Null(message = "email should be absent in request", groups = OnUpdate.class)
    private String email;
    @NotBlank(message = "role is mandatory", groups = OnCreate.class)
    private String role;

    @Null(message = "email should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "phone is mandatory", groups = OnCreate.class)
    @ContactnumberConstraint(message = "phone regex", groups = OnCreate.class)
    private String phone;

    @Null(message = "email should be absent in request", groups = OnUpdate.class)
    @NotBlank(message = "password is mandatory", groups = OnCreate.class)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "password should only contain at least 1 big letter, 1 numbers and at least 8 letters ", groups = OnCreate.class)
    private String password;

    private double balance;
}
