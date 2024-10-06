package com.example.bdfinal.service;

import com.example.bdfinal.configuration.Constants;
import com.example.bdfinal.dto.ShoppingListRequestDto;
import com.example.bdfinal.dto.ShoppingListResponseDto;
import com.example.bdfinal.exception.DataCannotBeUpdatedException;
import com.example.bdfinal.exception.DataRepeatsItselfException;
import com.example.bdfinal.exception.NoDataFoundException;
import com.example.bdfinal.model.ShoppingListEntity;
import com.example.bdfinal.repository.ShoppingListRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {
    @Autowired
    private ShoppingListRepository shoppingListRepository;

    public ShoppingListResponseDto addShoppingList(ShoppingListRequestDto shoppingListRequestDto) {
        Optional<ShoppingListEntity> optionalItem = shoppingListRepository.findByName(shoppingListRequestDto.getName());
        if (optionalItem.isPresent()) {
            throw new DataRepeatsItselfException(Constants.Field.NAME.toString());
        }
        ShoppingListEntity shoppingListEntity = new ShoppingListEntity();
        shoppingListEntity.setName(shoppingListRequestDto.getName());
        shoppingListEntity.setQuantity(shoppingListRequestDto.getQuantity());
        shoppingListEntity.setUnitPrice(shoppingListRequestDto.getUnitPrice());
        shoppingListEntity.setTotalPrice(shoppingListRequestDto.getQuantity() * shoppingListRequestDto.getUnitPrice());

        ShoppingListEntity savedShoppingListEntity = shoppingListRepository.save(shoppingListEntity);

        ShoppingListResponseDto shoppingListResponseDto = new ShoppingListResponseDto();
        shoppingListResponseDto.setId(savedShoppingListEntity.getId());
        shoppingListResponseDto.setName(savedShoppingListEntity.getName());
        shoppingListResponseDto.setQuantity(savedShoppingListEntity.getQuantity());
        shoppingListResponseDto.setUnitPrice(savedShoppingListEntity.getUnitPrice());
        shoppingListResponseDto.setTotalPrice(savedShoppingListEntity.getTotalPrice());

        return shoppingListResponseDto;
    }

    public ShoppingListResponseDto updateShoppingList(Long itemId, ShoppingListRequestDto updateDto) {
        Optional<ShoppingListEntity> optionalItem = shoppingListRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            ShoppingListEntity item = optionalItem.get();
            if (updateDto.getName() != null) {
                item.setName(updateDto.getName());
            }
            if (updateDto.getQuantity() != null) {
                item.setQuantity(updateDto.getQuantity());
                if (updateDto.getUnitPrice() != null) {
                    item.setTotalPrice(item.getQuantity() * updateDto.getUnitPrice());
                }else {
                    item.setTotalPrice(item.getQuantity() * item.getUnitPrice());
                }
            }
            if (updateDto.getUnitPrice() != null) {
                item.setUnitPrice(updateDto.getUnitPrice());
                if (updateDto.getQuantity() != null) {
                    item.setTotalPrice(updateDto.getQuantity() * updateDto.getUnitPrice());
                }else {
                    item.setTotalPrice(item.getQuantity() * item.getUnitPrice());
                }
            }
            if (updateDto.getTotalPrice() != null) {
                throw new DataCannotBeUpdatedException(Constants.Field.TOTAL_PRICE.toString());
            }
            ShoppingListEntity updatedItem = shoppingListRepository.save(item);
            ShoppingListResponseDto responseDto = new ShoppingListResponseDto();
            BeanUtils.copyProperties(updatedItem, responseDto);
            return responseDto;
        } else {
            throw new NoDataFoundException();
        }
    }

    public String deleteShoppingList(Long itemId) {
        Optional<ShoppingListEntity> optionalItem = shoppingListRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            shoppingListRepository.deleteById(itemId);
            return "Item deleted successfully";
        } else {
            throw new NoDataFoundException();
        }
    }

    public ShoppingListResponseDto getShoppingListById(Long itemId) {
        Optional<ShoppingListEntity> optionalItem = shoppingListRepository.findById(itemId);
        if (optionalItem.isPresent()) {
            ShoppingListEntity item = optionalItem.get();
            ShoppingListResponseDto responseDto = new ShoppingListResponseDto();
            BeanUtils.copyProperties(item, responseDto);
            return responseDto;
        } else {
            throw new NoDataFoundException();
        }
    }

    public List<ShoppingListResponseDto> getAllShoppingList() {
        return shoppingListRepository.findAll().stream()
                .toList()
                .stream()
                .map(item -> {
                    ShoppingListResponseDto responseDto = new ShoppingListResponseDto();
                    BeanUtils.copyProperties(item, responseDto);
                    return responseDto;
                })
                .toList();
    }
}
