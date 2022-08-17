package com.epam.repairstudio.dto;



import com.epam.repairstudio.dto.group.OnCreate;
import com.epam.repairstudio.dto.group.OnUpdate;
import com.epam.repairstudio.model.User;
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
    long requestid;
    @Null(message = "user should be absent in request", groups = OnUpdate.class)
    User user;
    @NotBlank(message = "description is mandatory", groups = OnCreate.class)
    @Null(message = "email should be absent in request", groups = OnUpdate.class)
    String requestDescr;

    @NumberFormat
    double price;

    @NotBlank(message = "status is mandatory", groups = OnCreate.class)
    String status;

}
