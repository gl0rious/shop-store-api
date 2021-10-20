package com.store.shop.controller;

import com.store.shop.model.Product;
import com.store.shop.model.ShoppingOrder;
import com.store.shop.model.User;
import com.store.shop.service.OrderItemService;
import com.store.shop.service.ProductService;
import com.store.shop.service.ShoppingOrderService;
import com.store.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class ShoppingOrderController {
    @Autowired
    private ShoppingOrderService orderService;
    @Autowired
    private UserService userService;

    @GetMapping("/api/orders")
    public List<ShoppingOrder> getAllOrders(Principal principal) {
        User user = userService.findUserByName(principal.getName());
        return orderService.findAllByUserId(user.getId());
    }
}
