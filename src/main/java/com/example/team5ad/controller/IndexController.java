package com.example.team5ad.controller;

import com.example.team5ad.entity.PropertyListing;
import com.example.team5ad.entity.User;
import com.example.team5ad.entity.Wish;
import com.example.team5ad.repo.PropertyListingRepository;
import com.example.team5ad.repo.UserRepository;
import com.example.team5ad.repo.WishDao;
import com.example.team5ad.service.PropertyListingService;
import com.example.team5ad.service.impl.PropertyListingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class IndexController {

    @Autowired
    private WishDao wishDao;

    @Autowired
    private PropertyListingRepository prepo;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PropertyListingServiceImpl propertyListingService;


    @RequestMapping("index")
    public String index(ModelMap modelMap, HttpSession session) {
        // 查询当前用户所喜欢的商品列表

        org.springframework.security.core.userdetails.User user1 = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(user1.getUsername());
        session.setAttribute("user", user);
        List<PropertyListing> allProperty =  prepo.findAll();
        modelMap.addAttribute("allProperty",allProperty);

        // 随机轮播图
        Map<Long, List<String>> imagePathMap = propertyListingService.getImagePathsForPropertyListings(allProperty);
        List<String> bannerImages = new ArrayList<>();
        Set<Map.Entry<Long, List<String>>> entries = imagePathMap.entrySet();
        for (Map.Entry<Long, List<String>> entry : entries) {
            List<String> value = entry.getValue();
            bannerImages.addAll(value);
        }
        // 打乱顺序
        Collections.shuffle(bannerImages);
        if (bannerImages.size() > 5) {
            bannerImages = bannerImages.subList(0, 5);
        } else {
            bannerImages = bannerImages.subList(0, bannerImages.size());
        }
        modelMap.put("imageList", bannerImages);

        List<Wish> wishList = wishDao.findByUserId(user.getId());
        modelMap.addAttribute("wishList", wishList);

        imagePathMap = propertyListingService.getImagePathsForPropertyListings(allProperty);
        modelMap.addAttribute("imageMap", imagePathMap);

        return "index";
    }

}
