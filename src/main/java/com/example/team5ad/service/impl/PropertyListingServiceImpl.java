package com.example.team5ad.service.impl;

import com.example.team5ad.entity.PropertyListing;
import com.example.team5ad.repo.PropertyListingRepository;
import com.example.team5ad.repo.UserRepository;
import com.example.team5ad.service.PropertyListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*Used by Ee Loon*/

@Service
public class PropertyListingServiceImpl implements PropertyListingService {

    @Autowired
    PropertyListingRepository prepo;

    @Autowired
    UserRepository userRepository;

    //Get all listings
    public List<PropertyListing> findAll() {
        return prepo.findAll();
    }


    //Get all listings in pages
    public Page<PropertyListing> findAllWithPageAndSort(int pageNumber, String sortField, String sortDir) {
        Sort sort;
        if(sortDir.equals("asc")){
            sort = Sort.by(sortField).ascending();
        }
        else{
            sort = Sort.by(sortField).descending();
        }
        Pageable pageable = PageRequest.of(pageNumber - 1, 6, sort);
        return prepo.findAll(pageable);
    }


    //Get listings by address keyword search
    public List<PropertyListing> findByKeyword(String keyword){
        return prepo.findByKeyword(keyword);
    }

    public Page<PropertyListing> findByKeywordWithPageAndSort(String keyword, int pageNumber, String sortField, String sortDir){
        Sort sort;
        if(sortDir.equals("asc")){
            sort = Sort.by(sortField).ascending();
        }
        else{
            sort = Sort.by(sortField).descending();
        }
        Pageable pageable = PageRequest.of(pageNumber - 1, 6, sort);
        return prepo.findByKeyword2(keyword, pageable);
    }


    public PropertyListing findPropertyListingById(long id){
        return prepo.findById(id).get();
    }

    public void savePropertyListing(PropertyListing propertyListing){
        prepo.save(propertyListing);
    }

    public void deletePropertyListingById(long id){
        PropertyListing propertyListing = findPropertyListingById(id);
        prepo.delete(propertyListing);
    };

    //Using multipartfile
    public List<String> getPhotosImagePathOfPropList(long id){
        List<String> photosImagePath = new ArrayList<>();

        PropertyListing propertyListing = prepo.getById(id);
        List<String> imageFileNames = propertyListing.getImageFileNames();

        if(imageFileNames == null){
            return null;
        }
        else{
            for(String imageFileName : imageFileNames){
                photosImagePath.add("/prop-list-photos/" + id + "/" + imageFileName);
            }
        }
        return photosImagePath;
    }

    //using multipartfile
    //Get image path strings from list of propertylisting ids
    public Map<Long, List<String>> getImagePathsForPropertyListings(List<PropertyListing> listings){
        Map<Long, List<String>> imagePathMap = new HashMap<>();
        List<Long> propertyListingIds = listings.stream()
                .map(a -> a.getId())
                .collect(Collectors.toList());
        for(Long propertyListId : propertyListingIds) {
            List<String> imagesForProp = getPhotosImagePathOfPropList(propertyListId);
            imagePathMap.put(propertyListId, imagesForProp);
        }
        return imagePathMap;
    }

    //NEW! Added by XH
    public List<PropertyListing>findPropertyListingByUserId(Long userId){
        return prepo.findPropertyListingByUserId(userId);
    }

}
