package com.example.team5ad.controller;

import com.example.team5ad.entity.*;
import com.example.team5ad.entity.DistrictLocation;
import com.example.team5ad.entity.HousingType;
import com.example.team5ad.helper.TypeUserEnum;
import com.example.team5ad.repo.PropertyListingRepository;
import com.example.team5ad.repo.UserRepository;
import com.example.team5ad.service.impl.ImageServiceImpl;
import com.example.team5ad.service.impl.PropertyListingServiceImpl;
import com.example.team5ad.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: lxh
 * @Dare: 12/2/2022 - 02 - 12 - 10:34 PM
 * @Description: com.example.team5ad.controller
 * @version: 1.0
 */

@RestController
@RequestMapping(value="/android")
public class AndroidController {

    //++Services and Repo
    @Autowired
    PropertyListingRepository prepo;
    @Autowired
    PropertyListingServiceImpl pservice;
    @Autowired
    ImageServiceImpl imageService;

    @Autowired
    UserServiceImpl uService;
    @Autowired
    UserRepository uRepo;


    //XH methods//

    //NEW! Return UserId through login username
    @PostMapping("/userId")
    public User findUserId(@RequestParam("username") String username){
        User user = uRepo.findByUsername(username);
        //userId
        return user;
    }

    //NEW! Return list of PropertyListing tied to the UserId
    @GetMapping("/allpl/{id}")
    public List<PropertyListing> findAllMyListings(@PathVariable("id") String id){
        Long u_id = Long.parseLong(id);
        return prepo.findPropertyListingByUserId(u_id);//get all listings linked to userId
    }

    //Return one PropertyListing tied to listing's Id
    @GetMapping("/findpl/{id}")
    public PropertyListing findOneMyListing(@PathVariable("id") String id){
        Long pl_id = Long.parseLong(id);
        return prepo.findById(pl_id).get();
    }

    //Returns list of Images for PropertyListing tied to listing's Id
    @GetMapping("/onepl_allimg2/{id}")
    public Map<Long, List<String>> findOneMyListingAllImages2(@PathVariable("id") String id) {
        Long pl_id = Long.parseLong(id);
        PropertyListing p = prepo.findById(pl_id).get();
        List<String> imageFileNames = p.getImageFileNames();
        //the result to return:
        Map<Long, List<String>> onepl_allimg2 = new HashMap<>();

        onepl_allimg2.put(pl_id, imageFileNames);

        return onepl_allimg2; //for checking purpose in Log
    }

    //Receive values from Android and create new PropertyLising object.
    @PostMapping("/addpropertylisting")
    public PropertyListing addPropertyListing(
            @RequestParam("housingType") String housingType,
            @RequestParam("districtLocation") String districtLocation,
            @RequestParam("address") String address,
            @RequestParam("size") String size,
            @RequestParam("askingPrice") String askingPrice,
            @RequestParam("priceNegotiable") String priceNegotiable,
            @RequestParam("description") String description,
            @RequestParam("userId") String userId){

        //getting all values and converting to the correct type.
        HousingType housingTypeEnum = HousingType.valueOf(housingType);
        DistrictLocation districtLocationEnum = DistrictLocation.valueOf(districtLocation);
        //String address - no conversion needed
        Long sizeL = Long.parseLong(size);
        Long askingPriceL = Long.parseLong(askingPrice);
        Boolean priceNegotiableB = Boolean.parseBoolean(priceNegotiable);
        //String description - no conversion needed
        Long userIdL = Long.parseLong(userId);
     //   User user = uRepo.getById(userIdL);

        //Create the new PropertyListing with constructor
        PropertyListing addpl = new PropertyListing(
                housingTypeEnum,
                districtLocationEnum,
                address,
                sizeL,
                askingPriceL,
                priceNegotiableB,
                description,
                userIdL);
        prepo.saveAndFlush(addpl);

        return addpl;
    }

    //Add list of Images for mew PropertyListing
    @PostMapping("/addimg_onepl/{id}")
    public List<String> addImagesToPropertyListing(
            @RequestParam("file") MultipartFile[] files,
            @PathVariable("id") String id){
        //Obtain property listing
        Long pl_id = Long.parseLong(id);
        PropertyListing targetpropertylisting = pservice.findPropertyListingById(pl_id);

        //Save multipartfile
        try {
            imageService.saveFileToDirectoryAndListing(files, pl_id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Get list of imagepaths of images
        List<String> images = pservice.getPhotosImagePathOfPropList(pl_id);

        return images; //for checking purpose in Log
    }

    //to edit the delete method
    //download a copy of zip file from github before cleanup
    @PostMapping("/deletepl/{id}")
    public String deleteMyListing(@PathVariable("id") String id){
        //Delete PropertyListing
        Long pl_id = Long.parseLong(id);
        pservice.deletePropertyListingById(pl_id);
        //Delete Images associated to PropertyListing from directory
        imageService.deleteAllFilesOfPropListFromDirectory(pl_id);
        return "Deleted";
    }

    //NOTE: Return all PropertyListings in database - for backend checking purposes.
    @GetMapping("/allpl")
    public List<PropertyListing> findAllListings(){
        return prepo.findAll();
    }

    //end XH's methods//

    // here is the Android register function version 1
    // not do checking at this stage -- check username already exist or not
    @GetMapping("/register/{username}/{password}")
    public String register(@PathVariable("username") String username,
                           @PathVariable("password") String password){
        User user = uRepo.findByUsername(username);
        if(user!=null){
            return "Already Exist";
        }
        UserRegistrationDto newUser = new UserRegistrationDto();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setType(TypeUserEnum.BUSINESS.toString());
        uService.save(newUser);
        return "Success";
    }
    @GetMapping("/login/{username}/{password}")
    public String login(@PathVariable("username") String username,
                        @PathVariable("password") String password) {
        User user = uRepo.findByUsername(username);
        if (user == null) {
            return "Not Exist";
        }
        if (!user.getType().equals("BUSINESS")) {
            return "No Permission";
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String targetPassword = encoder.encode(password);
        if (encoder.matches(password, user.getPassword())) {
            return "Success";
        } else {
            return "Fail";
        }
    }

}

