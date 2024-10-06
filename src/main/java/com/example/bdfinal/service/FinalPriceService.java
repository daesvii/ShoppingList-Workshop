package com.example.bdfinal.service;

import com.example.bdfinal.dto.FinalPriceResponseDto;
import com.example.bdfinal.model.ShoppingListEntity;
import com.example.bdfinal.repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinalPriceService {
    @Autowired
    private ShoppingListRepository shoppingListRepository;

    public FinalPriceResponseDto getFinalPrice() {
        List<ShoppingListEntity> shoppingListEntities = shoppingListRepository.findAll();

        int totalProducts = shoppingListEntities.size();
        int totalArticles = 0;
        double finalPrice = 0;

        for (ShoppingListEntity shoppingListEntity : shoppingListEntities) {
            finalPrice += shoppingListEntity.getTotalPrice();
            totalArticles += shoppingListEntity.getQuantity();
        }

        FinalPriceResponseDto finalPriceResponseDto = new FinalPriceResponseDto();
        finalPriceResponseDto.setTotalArticles(totalArticles);
        finalPriceResponseDto.setFinalPrice(finalPrice);
        finalPriceResponseDto.setTotalProducts(totalProducts);
        return finalPriceResponseDto;
    }
}
