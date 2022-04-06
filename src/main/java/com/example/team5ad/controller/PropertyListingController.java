package com.example.team5ad.controller;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.example.team5ad.repo.UserRepository;
import com.example.team5ad.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.team5ad.entity.PropertyListing;
import com.example.team5ad.repo.PropertyListingRepository;
import com.example.team5ad.service.impl.ImageServiceImpl;
import com.example.team5ad.service.impl.PropertyListingServiceImpl;
import com.example.team5ad.validator.PropertyListingValidator;

@Controller
@RequestMapping("/property")
public class PropertyListingController {

    @InitBinder("propertylisting")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new PropertyListingValidator());
    }

    @Autowired
    PropertyListingServiceImpl propertyListingService;
    @Autowired
    ImageServiceImpl imageService;

    @Autowired
    UserRepository userRepository;
    @Autowired
    PropertyListingRepository prepo;


    @GetMapping("/allmylistings")
    public String viewAllMyListings( Model model) {
        List<PropertyListing> allMyListings = new ArrayList<>();

        //Authenticate user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal(); //spring security user details
        com.example.team5ad.entity.User currentUser = userRepository.findByUsername(user.getUsername());

        allMyListings = prepo.findPropertyListingByUserId(currentUser.getId());

        model.addAttribute("allMyListings", allMyListings);

        Map<Long, List<String>> imagePathMap = new HashMap<>();
        imagePathMap = propertyListingService.getImagePathsForPropertyListings(allMyListings);

        model.addAttribute("imageMap", imagePathMap);

        return "allmylistings";
    }

    @RequestMapping("/allmylistings/search")
    public String searchListingsByAddress(Model model, String keyword) {
        //Authenticate user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal(); //spring security user details
        com.example.team5ad.entity.User currentUser = userRepository.findByUsername(user.getUsername());
        long currentUserId = currentUser.getId();

        //Get property listings by address search term
        List<PropertyListing> listings = new ArrayList<>();
        if (keyword != null) {
            List<PropertyListing> listingsWithKeyword = propertyListingService.findByKeyword(keyword);
            listings = listingsWithKeyword.stream()
                    .filter(a -> a.getUserId() == currentUserId)
                    .collect(Collectors.toList());
        } else {
            listings = prepo.findPropertyListingByUserId(currentUserId);
        }
        model.addAttribute("allMyListings", listings);

        Map<Long, List<String>> imagePathMap = new HashMap<>();
        imagePathMap = propertyListingService.getImagePathsForPropertyListings(listings);

        model.addAttribute("imageMap", imagePathMap);
        return "allmylistings";
    }

    @GetMapping("/newlistingform")
    public String loadNewListingForm(Model model) {
        //Authenticate user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal(); //spring security user details
        com.example.team5ad.entity.User currentUser = userRepository.findByUsername(user.getUsername());

        PropertyListing propertyListing = new PropertyListing();
        propertyListing.setUserId(currentUser.getId());
        model.addAttribute("propertylisting", propertyListing);
        return "listingform";
    }

    @PostMapping("/addnewlisting")
    public String addNewListing(@ModelAttribute("propertylisting") @Valid PropertyListing propertyListing,
                                BindingResult bingResult, Model model) {


        if (bingResult.hasErrors()) {
            return "listingform";
        }
        propertyListingService.savePropertyListing(propertyListing);

        model.addAttribute("propertylisting", propertyListing);
        return "listingformphoto";
    }

    @PostMapping("/addphototolisting/{id}")
    public String addPhotoToListing(@RequestParam("files") MultipartFile[] files, @PathVariable("id") long id,
                                    Model model) {

        //Property listing
        PropertyListing targetpropertylisting = propertyListingService.findPropertyListingById(id);
        model.addAttribute("propertylisting", targetpropertylisting);

        try {
            imageService.saveFileToDirectoryAndListing(files, id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Get list of imagepaths of images
        List<String> images = propertyListingService.getPhotosImagePathOfPropList(id);
        model.addAttribute("images", images);

        return "listingformsubmitted";
    }

    @GetMapping("/editlisting/{id}")
    public String editListing(@PathVariable("id") long id, Model model) {
        PropertyListing targetPropertyListing = propertyListingService.findPropertyListingById(id);
        model.addAttribute("propertylisting", targetPropertyListing);

        List<String> images = propertyListingService.getPhotosImagePathOfPropList(id);
        model.addAttribute("imageStrings", images);

        return "listingedit";
    }


    @PostMapping("/editlisting/{id}")
    public String submitEditListing(@ModelAttribute("propertylisting") @Valid PropertyListing propertyListing,
                                    BindingResult bingResult, Model model, @PathVariable("id") long id,
                                    @RequestParam("files") MultipartFile[] files) {
        if (bingResult.hasErrors()) {
            return "listingedit";
        }
        PropertyListing propertyListingFromDB = propertyListingService.findPropertyListingById(id);
        List<String> existingImageFileNames = propertyListingFromDB.getImageFileNames();
        propertyListing.setImageFileNames(existingImageFileNames);
        //Add new photos (if available) and save entire entity
        try {
            imageService.editListingWithNewImages(files, propertyListing);
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("propertylisting", propertyListing);

        //Get list of imagepaths of images
        List<String> images = propertyListingService.getPhotosImagePathOfPropList(id);
        model.addAttribute("images", images);

        return "listingformsubmitted";
    }


    @RequestMapping("/editlisting/deleteimage/{id}/{filename}")
    @ResponseBody
    public String deletePhotoFromListing(@PathVariable("id") long propListId,
                                         @PathVariable("filename") String filename) {
        imageService.deleteFileFromDirectoryAndListing(propListId, filename);
        return "imagedeleted";
    }

    @RequestMapping("/deletelisting/{id}")
    @ResponseBody
    public String deleteListing(@PathVariable("id") long id) {
        //Delete prop listing
        propertyListingService.deletePropertyListingById(id);

        //Delete photos associated to prop list from directory
        imageService.deleteAllFilesOfPropListFromDirectory(id);

        return "proplistdeleted";
    }

    @RequestMapping("detail")
    public String detail(long propertyId, ModelMap modelMap) {
        Optional<PropertyListing> byId = prepo.findById(propertyId);
        PropertyListing propertyListing = byId.get();
        modelMap.addAttribute("property", propertyListing);
        List<PropertyListing> list = new ArrayList<>();
        list.add(propertyListing);
        Map<Long, List<String>> imagePathsForPropertyListings =
                propertyListingService.getImagePathsForPropertyListings(list);
        modelMap.addAttribute("imageList", imagePathsForPropertyListings.get(propertyListing.getId()));
        return "detail";
    }

}
