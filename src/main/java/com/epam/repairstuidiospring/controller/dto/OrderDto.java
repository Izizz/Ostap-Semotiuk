package com.epam.repairstuidiospring.controller.dto;

import com.epam.repairstuidiospring.controller.dto.group.OnCreate;
import com.epam.repairstuidiospring.controller.dto.group.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    @JsonProperty(access = READ_ONLY)
    long requestId;
    @Null(message = "user_id should be absent in request", groups = OnUpdate.class)
    long user_id;
    @NotBlank(message = "description is mandatory", groups = OnCreate.class)
    @Null(message = "email should be absent in request", groups = OnUpdate.class)
    String requestDescr;
    @NotBlank(message = "master  is mandatory", groups = OnCreate.class)
    String master;
    @NumberFormat
    double price;

    String feedback;

    @NotBlank(message = "Lastname is mandatory", groups = OnCreate.class)
    String status;

}
