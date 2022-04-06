package com.example.team5ad.service.impl;

import com.example.team5ad.entity.PrivateProperty;
import com.example.team5ad.repo.PrivatePropertyDAO;
import com.example.team5ad.service.PrivatePropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @Author: Xin Yuchen
 * @Date: 2022/2/10 - 02 - 10 - 1:40
 * @Description: Any Ques or Concerns, plz contact e0838400@u.nus.edu
 */
@Service
public class PrivatePropertyServiceImpl implements PrivatePropertyService {
    @Autowired
    PrivatePropertyDAO pDAO;
    @Override
    public Page<PrivateProperty> getResultListSortedByDistrict(int pageNum, int pageSize, String district) {
        // cuz we want both do pagination and sort, we should create a example to tell jpa to do pagination based on this example
        PrivateProperty privateProperty = new PrivateProperty();
        privateProperty.setDistrict(district);

        ExampleMatcher districtMatcher = ExampleMatcher.matching()
                .withMatcher("district",ExampleMatcher.GenericPropertyMatchers.exact());
        Example<PrivateProperty> example = Example.of(privateProperty,districtMatcher);
        PageRequest pageRequest = PageRequest.of(pageNum,pageSize);
        Page<PrivateProperty> projects = null;
        // here we use findAll to do the pagination, and also check the sort condition first
        if(district==null || district.isBlank()){
            projects = pDAO.findAll(pageRequest);
        }else{
            projects = pDAO.findAll(example,pageRequest);
        }
        return projects;
    }
}
