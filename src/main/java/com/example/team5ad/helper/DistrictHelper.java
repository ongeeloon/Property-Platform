package com.example.team5ad.helper;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Xin Yuchen
 * @Date: 2022/2/10 - 02 - 10 - 20:31
 * @Description: Any Ques or Concerns, plz contact e0838400@u.nus.edu
 */

public class DistrictHelper {
    private String districtNum;
    private String districtName;

    public DistrictHelper() {
    }

    public String getDistrictNum() {
        return districtNum;
    }

    public void setDistrictNum(String districtNum) {
        this.districtNum = districtNum;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Override
    public String toString() {
        return "DistrictHelper{" +
                "districtNum='" + districtNum + '\'' +
                ", districtName='" + districtName + '\'' +
                '}';
    }
}
