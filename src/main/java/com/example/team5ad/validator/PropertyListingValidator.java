package com.example.team5ad.validator;

import com.example.team5ad.entity.PropertyListing;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/*Used by Ee Loon*/

public class PropertyListingValidator implements Validator {

    public boolean supports(Class clazz){
        return PropertyListing.class.equals(clazz);
    }

    public void validate(Object obj, Errors e){
        PropertyListing proplist = (PropertyListing) obj;
        if(proplist.getHousingType() == null){
            e.rejectValue("housingType", "housingType", "Housing Type cannot be blank");
        }
        if(proplist.getDistrictLocation() == null){
            e.rejectValue("districtLocation", "districtLocation", "Location district cannot be blank");
        }
        if(proplist.getAddress() == null){
            e.rejectValue("address", "address", "Address cannot be blank");
        }
        if(proplist.getSize() < 1){
            e.rejectValue("size", "size", "Property size cannot be zero");
        }
        if(proplist.getAskingPrice() < 1){
            e.rejectValue("askingPrice", "askingPrice", "Asking price cannot be zero");
        }
        if(proplist.getDescription().length() > 2000){
            e.rejectValue("description", "description", "Character limit: 2000");
        }

    }
}
