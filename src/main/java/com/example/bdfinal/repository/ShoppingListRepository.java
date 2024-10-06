package com.example.bdfinal.repository;

import com.example.bdfinal.model.ShoppingListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, Long> {

    Optional<ShoppingListEntity> findByName(String name);
}
