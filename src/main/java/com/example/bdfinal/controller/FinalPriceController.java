package com.example.bdfinal.controller;

import com.example.bdfinal.dto.FinalPriceResponseDto;
import com.example.bdfinal.service.FinalPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/final-price")
public class FinalPriceController {
    @Autowired
    private FinalPriceService finalPriceService;

    @GetMapping()
    public FinalPriceResponseDto getFinalPrice() {
        return finalPriceService.getFinalPrice();
    }
}
