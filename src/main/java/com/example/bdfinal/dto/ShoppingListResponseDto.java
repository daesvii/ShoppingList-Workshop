package com.example.bdfinal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingListResponseDto {
    private Long id;
    private String name;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
}
