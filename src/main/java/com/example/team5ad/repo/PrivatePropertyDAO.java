package com.example.team5ad.repo;

import com.example.team5ad.entity.PrivateProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Xin Yuchen
 * @Date: 2022/2/10 - 02 - 10 - 1:34
 * @Description: Any Ques or Concerns, plz contact e0838400@u.nus.edu
 */
@Repository
public interface PrivatePropertyDAO extends JpaRepository<PrivateProperty,String> {
    public PrivateProperty findResultByProject(String projectName);
    public PrivateProperty findPrivatePropertyByProject(String project);

}
