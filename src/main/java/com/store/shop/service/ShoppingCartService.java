package com.store.shop.service;

import com.store.shop.model.*;
import com.store.shop.repository.OrderItemRepository;
import com.store.shop.repository.ProductRepository;
import com.store.shop.repository.ShoppingCartRepository;
import com.store.shop.repository.ShoppingOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository cartRepo;
    @Autowired
    private OrderItemRepository itemRepo;
    @Autowired
    private ShoppingOrderRepository orderRepo;
    @Autowired
    private ProductService productService;

    public ShoppingCart findByUserId(Long userId){
        return cartRepo.findByUserId(userId);
    }

    public OrderItem addToUserCart(Long userId, Long productId, int quantity){
        ShoppingCart cart = findByUserId(userId);
        Product product = productService.findById(productId);
        OrderItem item = new OrderItem(cart.getOpenOrder(), product, quantity);
        return itemRepo.save(item);
    }

    public OrderItem update(OrderItem item) {
        return itemRepo.save(item);
    }

    public boolean isEmpty(ShoppingCart cart) {
        return cart.getOpenOrder().getItems().isEmpty();
    }

    @Transactional
    public ShoppingOrder checkout(ShoppingCart cart) {
        ShoppingOrder order = cart.getOpenOrder();
        order.setCompleted(true);
        order.setOrderDate(LocalDateTime.now());
        orderRepo.save(order);

        ShoppingOrder newCartOrder = new ShoppingOrder();
        newCartOrder.setUser(cart.getUser());
        orderRepo.save(newCartOrder);
        cart.setOpenOrder(newCartOrder);
        cartRepo.save(cart);
        return order;
    }

    @Transactional
    public void clear(ShoppingCart cart) {
        itemRepo.deleteAllInBatch(cart.getOpenOrder().getItems());
    }
}
