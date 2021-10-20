package com.store.shop.service;

import com.store.shop.model.OrderItem;
import com.store.shop.model.ShoppingOrder;
import com.store.shop.repository.ShoppingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingOrderService {
    @Autowired
    private ShoppingOrderRepository orderRepo;

    public List<ShoppingOrder> findAll() {
        return orderRepo.findAll();
    }

    public List<ShoppingOrder> findAllByUserId(Long userId) {
        return orderRepo.findAllByUserId(userId);
    }
}
