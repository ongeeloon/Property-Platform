package com.example.team5ad.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * @Author: Xin Yuchen
 * @Date: 2022/2/10 - 02 - 10 - 1:26
 * @Description: Any Ques or Concerns, plz contact e0838400@u.nus.edu
 */
@Entity
public class PrivateProperty {
    @Id
    private String project;
    private String street;
    private String x;
    private String y;
    @OneToMany(mappedBy = "privateProperty")
    private Collection<Transaction> transaction;
    private String marketSegment;
    private String district;

    public PrivateProperty() {
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public Collection<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(Collection<Transaction> transaction) {
        this.transaction = transaction;
    }

    public String getMarketSegment() {
        return marketSegment;
    }

    public void setMarketSegment(String marketSegment) {
        this.marketSegment = marketSegment;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "PrivateProperty{" +
                "project='" + project + '\'' +
                ", street='" + street + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", marketSegment='" + marketSegment + '\'' +
                ", district='" + district + '\'' +
                '}';
    }
}
