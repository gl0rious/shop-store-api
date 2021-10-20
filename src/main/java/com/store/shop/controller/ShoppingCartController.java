package com.store.shop.controller;

import com.store.shop.exception.CartIsEmptyException;
import com.store.shop.exception.OrderItemNotAccessibleException;
import com.store.shop.exception.OrderItemNotFoundException;
import com.store.shop.exception.ProductNotFoundException;
import com.store.shop.model.*;
import com.store.shop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService cartService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderItemService itemService;

    @GetMapping("/api/cart")
    public ResponseEntity<?> getCartItems(Principal principal) {
        User user = userService.findUserByName(principal.getName());
        ShoppingCart cart = cartService.findByUserId(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/api/cart")
    public ResponseEntity<?> addItemToCart(Principal principal,
                          @Valid @RequestBody OrderItemInfo itemInfo) {
        User user = userService.findUserByName(principal.getName());
        if(!productService.exist(itemInfo.getProductId()))
            throw new ProductNotFoundException();
        OrderItem item = cartService.addToUserCart(user.getId(),
                itemInfo.getProductId(), itemInfo.getQuantity());
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    @PutMapping("/api/cart")
    public ResponseEntity<?> updateItemInCart(Principal principal,
                                              @Valid @RequestBody OrderItemInfo itemInfo) {
        User user = userService.findUserByName(principal.getName());
        if(!itemService.exist(itemInfo.getItemId()))
            throw new OrderItemNotFoundException();
        if(itemService.ownedBy(user.getId(), itemInfo.getItemId()))
            throw new OrderItemNotAccessibleException();
        OrderItem item = itemService.updateFromInfo(itemInfo);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping("/api/cart/checkout")
    public ResponseEntity<?> cartCheckout(Principal principal) {
        User user = userService.findUserByName(principal.getName());
        ShoppingCart cart = cartService.findByUserId(user.getId());
        if(cartService.isEmpty(cart))
            throw new CartIsEmptyException();
        ShoppingOrder checkedOrder = cartService.checkout(cart);
        Map<String, String> info = new HashMap<>();
        info.put("status","Checkout complete");
        info.put("order_id",checkedOrder.getId().toString());
        return new ResponseEntity<>(info,
                HttpStatus.OK);
    }

    @DeleteMapping("/api/cart")
    public ResponseEntity<?> clearCart(Principal principal) {
        User user = userService.findUserByName(principal.getName());
        ShoppingCart cart = cartService.findByUserId(user.getId());
        if(cartService.isEmpty(cart))
            throw new CartIsEmptyException();
        cartService.clear(cart);
        return new ResponseEntity<>("Cart cleared", HttpStatus.OK);
    }

//    @GetMapping("/users/{userId}/cart")
//    public ResponseEntity<?> getCartItems(@PathVariable Long userId) {
//        if(!userService.exist(userId))
//            return new ResponseEntity<>("user not logged-in",
//                    HttpStatus.UNAUTHORIZED);
//        ShoppingCart cart = cartService.findByUserId(userId);
//        return new ResponseEntity<>(cart, HttpStatus.OK);
//    }

//    @PostMapping("/users/{userId}/cart")
//    public ResponseEntity<?> addItemToCart(@PathVariable Long userId,
//                                   @Valid @RequestBody OrderItemInfo itemInfo) {
//        if(!userService.exist(userId))
//            return new ResponseEntity<>("user not logged-in",
//                    HttpStatus.UNAUTHORIZED);
//        if(!productService.exist(itemInfo.getProductId()))
//            return new ResponseEntity<>("product does not exist",
//                    HttpStatus.BAD_REQUEST);
//        OrderItem item = cartService.addToUserCart(userId,
//                itemInfo.getProductId(), itemInfo.getQuantity());
//        return new ResponseEntity<>(item, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/users/{userId}/cart")
//    public ResponseEntity<?> updateItemInCart(@PathVariable Long userId,
//                                   @Valid @RequestBody OrderItemInfo itemInfo) {
//        if(!userService.exist(userId))
//            return new ResponseEntity<>("user not logged-in",
//                    HttpStatus.UNAUTHORIZED);
//        if(!itemService.exist(itemInfo.getItemId()))
//            return new ResponseEntity<>("item does not exist",
//                    HttpStatus.NOT_FOUND);
//        if(itemService.ownedBy(userId, itemInfo.getItemId()))
//            return new ResponseEntity<>("item is not in user's cart",
//                    HttpStatus.FORBIDDEN);
//        OrderItem item = itemService.updateFromInfo(itemInfo);
//        return new ResponseEntity<>(item, HttpStatus.OK);
//    }
//
//    @PostMapping("/users/{userId}/cart/checkout")
//    public ResponseEntity<?> cartCheckout(@PathVariable Long userId) {
//        if(!userService.exist(userId))
//            return new ResponseEntity<>("user not logged-in",
//                    HttpStatus.UNAUTHORIZED);
//        ShoppingCart cart = cartService.findByUserId(userId);
//        if(cartService.isEmpty(cart))
//            return new ResponseEntity<>("cart is empty",
//                    HttpStatus.CONFLICT);
//        ShoppingOrder checkedOrder = cartService.checkout(cart);
//        Map<String, String> info = new HashMap<>();
//        info.put("status","checkout complete");
//        info.put("order_id",checkedOrder.getId().toString());
//        return new ResponseEntity<>(info,
//                HttpStatus.OK);
//    }
//
//    @DeleteMapping("/users/{userId}/cart")
//    public ResponseEntity<?> clearCart(@PathVariable Long userId) {
//        if(!userService.exist(userId))
//            return new ResponseEntity<>("user not logged-in",
//                    HttpStatus.UNAUTHORIZED);
//        ShoppingCart cart = cartService.findByUserId(userId);
//        if(cartService.isEmpty(cart))
//            return new ResponseEntity<>("cart is empty",
//                    HttpStatus.CONFLICT);
//        cartService.clear(cart);
//        return new ResponseEntity<>("cart cleared", HttpStatus.OK);
//    }



//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(Exception.class)
//    public Map<String, String> allUncaughtCases(Exception ex) {
//        Map<String, String> errors = new HashMap<>();
//        errors.put(ex.getClass().getSimpleName(), ex.getMessage());
//        return errors;
//    }
}
