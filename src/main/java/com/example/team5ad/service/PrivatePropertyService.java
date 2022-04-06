package com.example.team5ad.service;

import com.example.team5ad.entity.PrivateProperty;
import org.springframework.data.domain.Page;

/**
 * @Author: Xin Yuchen
 * @Date: 2022/2/10 - 02 - 10 - 1:40
 * @Description: Any Ques or Concerns, plz contact e0838400@u.nus.edu
 */
public interface PrivatePropertyService {
    Page<PrivateProperty> getResultListSortedByDistrict(int pageNum, int pageSize, String district);
}
