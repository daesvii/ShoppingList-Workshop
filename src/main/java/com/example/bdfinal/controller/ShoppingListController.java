package com.example.bdfinal.controller;

import com.example.bdfinal.dto.ShoppingListRequestDto;
import com.example.bdfinal.dto.ShoppingListResponseDto;
import com.example.bdfinal.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shoppinglist")
public class ShoppingListController {
    @Autowired
    private ShoppingListService shoppingListService;
    @PostMapping()
    public ShoppingListResponseDto addShoppingList(@RequestBody ShoppingListRequestDto shoppingListRequestDto) {
        return shoppingListService.addShoppingList(shoppingListRequestDto);
    }
    @PatchMapping("/{itemId}")
    public ShoppingListResponseDto updateShoppingList(@PathVariable Long itemId, @RequestBody ShoppingListRequestDto patchRequestDto) {
        return shoppingListService.updateShoppingList(itemId, patchRequestDto);
    }

    @DeleteMapping("/{itemId}")
    public String deleteShoppingList(@PathVariable Long itemId) {
        return shoppingListService.deleteShoppingList(itemId);
    }

    @GetMapping("/{itemId}")
    public ShoppingListResponseDto getShoppingList(@PathVariable Long itemId) {
        return shoppingListService.getShoppingListById(itemId);
    }

    @GetMapping
    public List<ShoppingListResponseDto> getAllShoppingList() {
        return shoppingListService.getAllShoppingList();
    }
}
