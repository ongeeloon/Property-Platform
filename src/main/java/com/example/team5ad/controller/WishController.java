package com.example.team5ad.controller;

import com.example.team5ad.entity.*;
import com.example.team5ad.repo.PropertyListingRepository;
import com.example.team5ad.repo.UserRepository;
import com.example.team5ad.repo.WishDao;
import com.example.team5ad.service.WishService;
import com.example.team5ad.service.impl.PropertyListingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("wish")
public class WishController {

    @Autowired
    private WishService wishService;
    @Autowired
    PropertyListingRepository propertyListingRepository;

    @Autowired
    private WishDao wishDao;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PropertyListingServiceImpl propertyListingService;

    @RequestMapping("wishList")
    public String wishList(HttpSession httpSession, ModelMap modelMap) {
        org.springframework.security.core.userdetails.User user1 = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(user1.getUsername());
        List<Wish> wishList = wishDao.findByUserId(user.getId());
        List<PropertyListing> allMyListings = new ArrayList<>();
        for (Wish wish : wishList) {
            allMyListings.add(wish.getPropertyListing());
        }
        modelMap.addAttribute("propertyList", allMyListings);
        Map<Long, List<String>> imageMap = new HashMap<>();
        List<Long> propertyListingIds = allMyListings.stream().map(a -> a.getId()).collect(Collectors.toList());
        //List<Image> images = imageRepository.findByPropertyListingIdIn(propertyListingIds);
        Map<Long, List<String>> imagePathMap = propertyListingService.getImagePathsForPropertyListings(allMyListings);
        modelMap.addAttribute("imageMap", imagePathMap);
        return "propertyList";
    }


    @RequestMapping("addWish")
    public String addWish(ModelMap modelMap, long propertyId, HttpSession session) {
        org.springframework.security.core.userdetails.User user1 = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(user1.getUsername());
        wishService.addWish(user, propertyId);
        return "redirect:/index";
    }

    @RequestMapping("removeWish")
    public String removeWish(ModelMap modelMap, long propertyId, HttpSession session) {
        org.springframework.security.core.userdetails.User user1 = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(user1.getUsername());
        wishService.removeWish(propertyId, user.getId());
        return "redirect:/wish/wishList";
    }


}
