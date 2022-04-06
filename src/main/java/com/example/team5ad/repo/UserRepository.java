package com.example.team5ad.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.team5ad.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	User findByUsername(String username);

	User findById(long userId);
	
}
