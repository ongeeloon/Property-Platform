package com.example.team5ad.entity;

/*Used by EeLoon*/

public enum HousingType {
    CONDO("Condominium"),
    APART("Apartment"),
    WALKUP("Walk-up"),
    CLUSTER("Cluster House"),
    TERRACE("Terrace"),
    SEMID("Semi-Detached House"),
    BGLW("Bungalow House"),
    SHPHSE("Shophouse"),
    HDB1("1-room HDB"),
    HDB2("2-room HDB"),
    HDB3("3-room HDB"),
    HDB4("4-room HDB"),
    HDB5("5-room HDB"),
    HDBJ("Jumbo HDB");

    private final String housingType;

    HousingType(String housingType){
        this.housingType = housingType;
    }

    public String getHousingType(){
        return housingType;
    }
}
