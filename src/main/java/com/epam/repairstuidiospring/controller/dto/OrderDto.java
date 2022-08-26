package com.epam.repairstuidiospring.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
   public int requestId;
   public int user_id;
   public String requestDescr;
   public String master;
   public double price;
   public String feedback;
   public String status;

}
