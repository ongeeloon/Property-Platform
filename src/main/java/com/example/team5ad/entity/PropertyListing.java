package com.example.team5ad.entity;


import com.example.team5ad.repo.UserRepository;
import com.example.team5ad.service.impl.UserServiceImpl;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;



@Entity
@Data
@NoArgsConstructor
public class PropertyListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private HousingType housingType;
    @NotNull
    private DistrictLocation districtLocation;
    @NotNull
    private String address;
    @NotNull
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(1)
    private long size;
    @NotNull
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(1)
    @Column(name = "asking_price")
    private long askingPrice;
    private boolean priceNegotiable;
    @Size(max = 2000)
    private String description;
    private long userId;
    @ElementCollection
    private List<String> imageFileNames;


    public PropertyListing(HousingType housingType, DistrictLocation districtLocation, String address, long size, long askingPrice) {
        this.housingType = housingType;
        this.districtLocation = districtLocation;
        this.address = address;
        this.size = size;
        this.askingPrice = askingPrice;
    }

    public PropertyListing(HousingType housingType, DistrictLocation districtLocation, String address, long size, long askingPrice, boolean priceNegotiable, String description) {
        this.housingType = housingType;
        this.districtLocation = districtLocation;
        this.address = address;
        this.size = size;
        this.askingPrice = askingPrice;
        this.priceNegotiable = priceNegotiable;
        this.description = description;
    }

    public PropertyListing(HousingType housingType, DistrictLocation districtLocation, String address, long size, long askingPrice, boolean priceNegotiable, String description, long userId) {
        this.housingType = housingType;
        this.districtLocation = districtLocation;
        this.address = address;
        this.size = size;
        this.askingPrice = askingPrice;
        this.priceNegotiable = priceNegotiable;
        this.description = description;
        this.userId = userId;
    }

    public PropertyListing(HousingType housingType, DistrictLocation districtLocation, String address, long size,
                           long askingPrice, boolean priceNegotiable, String description, long userId,
                           List<String> imageFileNames) {
        this.housingType = housingType;
        this.districtLocation = districtLocation;
        this.address = address;
        this.size = size;
        this.askingPrice = askingPrice;
        this.priceNegotiable = priceNegotiable;
        this.description = description;
        this.userId = userId;
        this.imageFileNames = imageFileNames;
    }

}
