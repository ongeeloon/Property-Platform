package com.example.team5ad.service.impl;

import com.example.team5ad.entity.PropertyListing;
import com.example.team5ad.entity.User;
import com.example.team5ad.entity.Wish;
import com.example.team5ad.repo.WishDao;
import com.example.team5ad.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishServiceImpl implements WishService {

    @Autowired
    private WishDao wishDao;

    @Override
    public void addWish(User user, long propertyId) {
        List<Wish> wishList = wishDao.findByUserId(user.getId());
        List<PropertyListing> propertyList = wishList.stream().map(Wish::getPropertyListing).collect(Collectors.toList());
        List<Long> propertyIdList = propertyList.stream().map(PropertyListing::getId).collect(Collectors.toList());
        if (!propertyIdList.contains(propertyId)) {
            Wish wish = new Wish();
            PropertyListing property = new PropertyListing();
            property.setId(propertyId);
            wish.setUser(user);
            wish.setPropertyListing(property);
            wish.setDate(new Date());
            wishDao.save(wish);
        }
    }

    @Override
    @Transactional
    public void removeWish(long propertyId, Long userId) {
        wishDao.deleteByPropertyListingIdAndUserId(propertyId, userId);
    }
}
