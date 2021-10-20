package com.store.shop.repository;

import com.store.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    User findById(Long id);

    boolean existsById(Long userId);
}