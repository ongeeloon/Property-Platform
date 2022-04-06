package com.example.team5ad.repo;

import com.example.team5ad.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author: Xin Yuchen
 * @Date: 2022/2/10 - 02 - 10 - 1:34
 * @Description: Any Ques or Concerns, plz contact e0838400@u.nus.edu
 */
@Repository
public interface TransactionDAO extends JpaRepository<Transaction,Integer> {
    @Query("SELECT t FROM Transaction t JOIN t.privateProperty p WHERE p.project = :projectName")
    public List<Transaction> findTransactionByProjectName(@Param("projectName") String projectName);

    @Query("SELECT t.contractTime FROM Transaction t JOIN t.privateProperty p WHERE p.project = :projectName")
    public List<Date> findContractTimeByProjectName(@Param("projectName") String projectName);
}
