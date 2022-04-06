package com.example.team5ad.service;

import com.example.team5ad.entity.PropertyListing;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface PropertyListingService {
    public List<PropertyListing> findAll();

    public List<PropertyListing> findByKeyword(String keyword);

    public void savePropertyListing(PropertyListing propertyListing);

    public PropertyListing findPropertyListingById(long id);

    public void deletePropertyListingById(long id);

    public Page<PropertyListing> findAllWithPageAndSort(int pageNumber, String sortField, String sortDir);

    //NEW! added by XH
    public  List<PropertyListing>findPropertyListingByUserId(Long userId);
}
