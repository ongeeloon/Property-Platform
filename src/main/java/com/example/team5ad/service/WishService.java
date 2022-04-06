package com.example.team5ad.service;

import com.example.team5ad.entity.User;

public interface WishService {
    void addWish(User user, long propertyId);

    void removeWish(long propertyId, Long userId);
}
