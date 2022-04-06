package com.example.team5ad.helper;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: Xin Yuchen
 * @Date: 2022/2/13 - 02 - 13 - 17:30
 * @Description: Any Ques or Concerns, plz contact e0838400@u.nus.edu
 */
public class EchartsHelper {
    private Date contractTime;
    private BigDecimal price;

    public Date getContractTime() {
        return contractTime;
    }

    public void setContractTime(Date contractTime) {
        this.contractTime = contractTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "EchartsHelper{" +
                "contractTime=" + contractTime +
                ", price='" + price + '\'' +
                '}';
    }
}
