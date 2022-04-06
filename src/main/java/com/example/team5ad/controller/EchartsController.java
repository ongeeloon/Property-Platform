package com.example.team5ad.controller;

import com.example.team5ad.entity.Transaction;
import com.example.team5ad.helper.EchartsHelper;
import com.example.team5ad.repo.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author: Xin Yuchen
 * @Date: 2022/2/13 - 02 - 13 - 16:25
 * @Description: Any Ques or Concerns, plz contact e0838400@u.nus.edu
 */
@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Autowired
    TransactionDAO tDAO;

    // this method is to send line chart json data to the Transaction detail page
    @RequestMapping("/linechart/{project}")
    public List<EchartsHelper> getLineChartJson(@PathVariable("project") String project){
        List<EchartsHelper> linechart = new ArrayList<>();
        List<Transaction> transactions = tDAO.findTransactionByProjectName(project);
        // sort here so that the chart can show the trend by date
        transactions.sort(new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                return o1.getContractTime().compareTo(o2.getContractTime());
            }
        });
        // we need a helper class to make sure we could both send date and price in one json
        for(Transaction t : transactions){
            EchartsHelper echartsHelper = new EchartsHelper();
            echartsHelper.setContractTime(t.getContractTime());
            echartsHelper.setPrice(t.getPrice().divide(t.getArea(),2));
            linechart.add(echartsHelper);
        }
        return linechart;
    }
    // this method is to send bar chart data to the Transaction details page
    // but due to the data set condition, I suggest do not put bar chart
    @RequestMapping("/barchart/{project}")
    public int[] getBarChartJson(@PathVariable("project") String project){
        String[] propertyTypes = {"Apartment","Condominium","Detached","Executive Condominium","Strata Detached","Strata Semi-detached","Semi-detached","Strata Terrace","Terrace"};
        int[] number = new int[9];
        String propertyType;
        List<Transaction> transactions = tDAO.findTransactionByProjectName(project);
        for(Transaction t : transactions){
            propertyType = t.getPropertyType();
            for(int i = 0 ; i < 9 ; i++){
                if(propertyType.equals(propertyTypes[i])){
                    number[i]++;
                    break;
                }
            }
        }
        return number;
    }

}
