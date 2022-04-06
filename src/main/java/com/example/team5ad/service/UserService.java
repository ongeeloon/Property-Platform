package com.example.team5ad.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.team5ad.entity.User;
import com.example.team5ad.entity.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
	
}
