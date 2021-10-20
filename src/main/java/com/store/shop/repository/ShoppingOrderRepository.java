package com.store.shop.repository;

import com.store.shop.model.ShoppingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingOrderRepository  extends JpaRepository<ShoppingOrder, Long> {
    List<ShoppingOrder> findAllByUserId(Long userId);
}
