package com.store.shop.controller;

import com.store.shop.exception.ProductNotFoundException;
import com.store.shop.model.Product;
import com.store.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/api/products")
    public List<Product> getAllProducts() {
        return service.findAllProducts();
    }
    @GetMapping("/api/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        if(!service.exist(id))
            throw new ProductNotFoundException();
        return service.findById(id);
    }
}
