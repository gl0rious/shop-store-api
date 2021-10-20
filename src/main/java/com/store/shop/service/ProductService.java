package com.store.shop.service;

import com.store.shop.model.Product;
import com.store.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepo;

    public Product save(Product product){
        return productRepo.save(product);
    }

    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }

    public Product findById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public boolean exist(Long itemId) {
        return findById(itemId)!=null;
    }
}
