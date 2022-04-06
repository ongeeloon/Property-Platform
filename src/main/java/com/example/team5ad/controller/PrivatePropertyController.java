package com.example.team5ad.controller;

import com.example.team5ad.entity.PrivateProperty;
import com.example.team5ad.entity.Transaction;
import com.example.team5ad.helper.DistrictHelper;
import com.example.team5ad.helper.ProjectHelper;
import com.example.team5ad.repo.TransactionDAO;
import com.example.team5ad.service.impl.PrivatePropertyServiceImpl;
import com.example.team5ad.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: Xin Yuchen
 * @Date: 2022/2/10 - 02 - 10 - 1:42
 * @Description: Any Ques or Concerns, plz contact e0838400@u.nus.edu
 */
@Controller
public class PrivatePropertyController {
    @Autowired
    PrivatePropertyServiceImpl privatePropertyService;
    @Autowired
    TransactionServiceImpl transactionService;


    @RequestMapping("/privateProperty")
    public String getPrivatePropertyPage(Model model,
                       @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       @RequestParam(value = "district",defaultValue = "") String district) {
        Page<PrivateProperty> projects = privatePropertyService.getResultListSortedByDistrict(pageNum, pageSize,district);
        // we need one more project helper class to help thymeleaf to do the sort when we change page
        DistrictHelper districtHelper = new DistrictHelper();
        districtHelper.setDistrictNum(district);
        model.addAttribute("district",districtHelper);
        model.addAttribute("projects", projects);
        return "private_property";
    }
    @RequestMapping("/privateProperty/transactionDetail")
    public String getTransactionPage(Model model,
                       @RequestParam(value = "pageNum",defaultValue = "0") int pageNum,
                       @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                       @RequestParam(value = "projectName") String projectName,
                                     @RequestParam(value = "sort",defaultValue = "all") String sort) {
        Page<Transaction> transactions = transactionService.getTransactionListSortedByProject(pageNum,pageSize,projectName,sort);
        ProjectHelper projectHelper = new ProjectHelper();
        // we need one more project helper class to help thymeleaf to do the sort when we change page
        // we could combine district helper and project helper into one
        projectHelper.setProjectName(projectName);
        projectHelper.setSort(sort);
        model.addAttribute("transactions",transactions);
        model.addAttribute("projectName",projectHelper);
        return "private_property_detail";
    }
}
