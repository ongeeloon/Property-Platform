package com.example.team5ad.controller;

import com.example.team5ad.entity.PropertyListing;
import com.example.team5ad.entity.User;
import com.example.team5ad.helper.PropListSearchHelper;
import com.example.team5ad.repo.PropertyListingRepository;
import com.example.team5ad.repo.UserRepository;
import com.example.team5ad.service.impl.ImageServiceImpl;
import com.example.team5ad.service.impl.PropertyListingServiceImpl;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/forsale")
public class PropertyViewingController {
    @Autowired
    PropertyListingRepository prepo;

    @Autowired
    PropertyListingServiceImpl propertyListingService;

    @Autowired
    ImageServiceImpl imageService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/listings")
    public String viewAllListings(Model model, String keyword){
        PropListSearchHelper propListSearchHelper = new PropListSearchHelper();
        return listByPage(model, 1, "id", "desc", "", propListSearchHelper);
    }

    @GetMapping("/listings/page/{pageNumber}")
    public String listByPage(Model model, @PathVariable("pageNumber") int pageNumber,
                             @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                             @RequestParam(value = "sortDir", defaultValue = "desc") String sortDir,
                             @RequestParam(value = "keyword", defaultValue = "") String keyword,
                             @ModelAttribute("proplistsearchhelper") PropListSearchHelper propListSearchHelper){

        //get pages
        int currentPage = pageNumber;
        Page<PropertyListing> page = propertyListingService.findByKeywordWithPageAndSort(keyword, currentPage, sortField, sortDir);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();

        //get listings from pages
        List<PropertyListing> listings = page.getContent();

        //get users from listings
        List<Long> userIds = listings.stream().map(PropertyListing::getUserId).collect(Collectors.toList());
        List<User> users = userIds.stream().map(a -> userRepository.getById(a)).collect(Collectors.toList());

        model.addAttribute("currentpage", currentPage);
        model.addAttribute("totalitems", totalItems);
        model.addAttribute("totalpages", totalPages);
        model.addAttribute("listings", listings);
        model.addAttribute("users", users);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("keyword", keyword);
        model.addAttribute("proplistsearchhelper", propListSearchHelper);

        Map<Long, List<String>> imagePathMap = new HashMap<>();
        imagePathMap = propertyListingService.getImagePathsForPropertyListings(listings);
        model.addAttribute("imageMap", imagePathMap);

        return "alllistings";
    }

    @RequestMapping("/listings/search")
    public String searchListingsByAddress(@ModelAttribute("proplistsearchhelper") PropListSearchHelper propListSearchHelper, Model model){
        int currentpage = propListSearchHelper.getCurrentpage();
        String sortField = propListSearchHelper.getSortField();
        String sortDir = propListSearchHelper.getSortDir();
        String keyword = propListSearchHelper.getKeyword();

        model.addAttribute("proplistsearchhelper", propListSearchHelper);

        return listByPage(model, currentpage, sortField, sortDir, keyword, propListSearchHelper);

    }

}
