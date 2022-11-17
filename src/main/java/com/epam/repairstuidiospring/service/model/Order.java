package com.epam.repairstuidiospring.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    long requestId;
    long user_id;
    String requestDescr;
    String master;
    double price;
    String feedback;
    String status;

}
