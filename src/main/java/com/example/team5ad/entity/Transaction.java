package com.example.team5ad.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * @Author: Xin Yuchen
 * @Date: 2022/2/10 - 02 - 10 - 1:27
 * @Description: Any Ques or Concerns, plz contact e0838400@u.nus.edu
 */
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    private BigDecimal area;
    private String floorRange;
    private String noOfUnits;
    @DateTimeFormat(pattern = "yyyy/MM")
    private Date contractTime;
    private String typeOfSale;
    private BigDecimal price;
    private String propertyType;
    private String district;
    private String typeOfArea;
    private String tenure;

    @ManyToOne
    private PrivateProperty privateProperty;

    public Transaction() {
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public BigDecimal getArea() {
        return area;
    }

    public String getFloorRange() {
        return floorRange;
    }

    public String getNoOfUnits() {
        return noOfUnits;
    }

    public String getTypeOfSale() {
        return typeOfSale;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getDistrict() {
        return district;
    }

    public String getTypeOfArea() {
        return typeOfArea;
    }

    public String getTenure() {
        return tenure;
    }

    public PrivateProperty getPrivateProperty() {
        return privateProperty;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public void setFloorRange(String floorRange) {
        this.floorRange = floorRange;
    }

    public void setNoOfUnits(String noOfUnits) {
        this.noOfUnits = noOfUnits;
    }

    public void setTypeOfSale(String typeOfSale) {
        this.typeOfSale = typeOfSale;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setTypeOfArea(String typeOfArea) {
        this.typeOfArea = typeOfArea;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public void setPrivateProperty(PrivateProperty privateProperty) {
        this.privateProperty = privateProperty;
    }

    public Date getContractTime() {
        return contractTime;
    }

    public void setContractTime(Date contractTime) {
        this.contractTime = contractTime;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "area='" + area + '\'' +
                ", floorRange='" + floorRange + '\'' +
                ", noOfUnits='" + noOfUnits + '\'' +
                ", contractTime=" + contractTime +
                ", typeOfSale='" + typeOfSale + '\'' +
                ", price='" + price + '\'' +
                ", propertyType='" + propertyType + '\'' +
                ", district='" + district + '\'' +
                ", typeOfArea='" + typeOfArea + '\'' +
                ", tenure='" + tenure + '\'' +
                '}';
    }
}
