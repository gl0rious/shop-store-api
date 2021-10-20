package com.store.shop.service;

import com.store.shop.model.User;
import com.store.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        if (user != null) {
            return new org.springframework.security.
                    core.userdetails.User(user.getUsername(), user.getPassword(),
                    Arrays.asList(new SimpleGrantedAuthority("USER")));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}