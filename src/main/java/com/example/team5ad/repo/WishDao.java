package com.example.team5ad.repo;

import com.example.team5ad.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface WishDao extends JpaRepository<Wish, Long> {

    List<Wish> findByUserId(Long userId);

    void deleteByPropertyListingIdAndUserId(long propertyListingId, Long UserId);

}
