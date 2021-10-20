package com.store.shop.repository;

import com.store.shop.model.ShoppingCart;
import com.store.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findByUser(User user);
    ShoppingCart findByUserId(Long userId);
}
