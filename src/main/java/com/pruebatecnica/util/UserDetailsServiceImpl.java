package com.pruebatecnica.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<GrantedAuthority> authorities = new ArrayList<>();

        if (username.equals("admin")) {
            return User.withUsername("admin").password("$2a$12$vyjjqzQ/4EizhPufY3yemuAPyVeCCpC.RE0rwuyv34WXJZOyecg4O").authorities("ADMIN").build();
        }

        if (username.equals("external")) {
            return User.withUsername("external").password("$2a$12$xY86i9Br2gt4iWJ74GUbjuAhmgXoxzSBeSEwzFVm6Ku2YOaNyfEKK").authorities("EXTERNAL").build();
        }

        throw new UsernameNotFoundException("User not found");
    }

}
