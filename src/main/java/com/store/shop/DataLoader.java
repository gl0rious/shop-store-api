package com.store.shop;

import com.store.shop.model.Product;
import com.store.shop.model.ShoppingCart;
import com.store.shop.model.ShoppingOrder;
import com.store.shop.model.User;
import com.store.shop.repository.ProductRepository;
import com.store.shop.repository.UserRepository;
import com.store.shop.service.ProductService;
import com.store.shop.service.ShoppingCartService;
import com.store.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader implements CommandLineRunner {
    private static final Logger log =
            LoggerFactory.getLogger(DataLoader.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingCartService cartService;

    @Override
    public void run(String... args) throws Exception {
        User user1 = userService.save(new User("sam", "password"));
        User user2 = userService.save(new User("marko", "password"));
        User user3 = userService.save(new User("dalila", "password"));

        Product product1 = productService.save(
                new Product(
                        "Red Jacket",
                        500,
                        "red-jacket.jpg",
                        "Comfy Red Jacket",
                        "Red jacket long description"));
        Product product2 = productService.save(
                new Product(
                        "Blue Pants",
                        250,
                        "blue-pants.jpg",
                        "Slim Blue Pants",
                        "Ble pants long description"));
        Product product3 = productService.save(
                new Product(
                        "Smart Watch",
                        800,
                        "smart-watch.jpg",
                        "Stylish Smart Watch",
                        "Smart watch long description"));

        ShoppingCart cart = cartService.findByUserId(user1.getId());
        cartService.addToUserCart(user1.getId(), product1.getId(), 5);
        cartService.addToUserCart(user1.getId(), product2.getId(), 2);
        cartService.addToUserCart(user1.getId(), product3.getId(), 1);
    }
}
