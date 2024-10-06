package com.example.bdfinal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinalPriceResponseDto {
    private int totalProducts;
    private int totalArticles;
    private double finalPrice;
}
