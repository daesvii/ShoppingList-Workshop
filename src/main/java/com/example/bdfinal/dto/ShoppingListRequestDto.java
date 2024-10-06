package com.example.bdfinal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingListRequestDto {
    private String name;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
}
