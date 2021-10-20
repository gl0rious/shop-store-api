package com.store.shop.service;

import com.store.shop.model.ShoppingCart;
import com.store.shop.model.ShoppingOrder;
import com.store.shop.model.User;
import com.store.shop.repository.ShoppingCartRepository;
import com.store.shop.repository.ShoppingOrderRepository;
import com.store.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ShoppingCartRepository cartRepo;
    @Autowired
    private ShoppingOrderRepository orderRepo;

    public User save(User user){
        userRepo.save(user);
        if(cartRepo.findByUser(user)==null){
            ShoppingCart cart = new ShoppingCart();
            cart.setUser(user);
            ShoppingOrder order = new ShoppingOrder();
            order.setUser(user);
            orderRepo.save(order);
            cart.setOpenOrder(order);
            cartRepo.save(cart);
        }
        return user;
    }

    public User findById(Long id){
        return userRepo.findById(id);
    }


    public boolean exist(Long userId) {
        return userRepo.existsById(userId);
    }

    public User findUserByName(String name){
        return userRepo.findByUsername(name);
    }
}
