package com.example.team5ad.service;

import com.example.team5ad.entity.PrivateProperty;
import com.example.team5ad.entity.Transaction;
import org.springframework.data.domain.Page;

/**
 * @Author: Xin Yuchen
 * @Date: 2022/2/11 - 02 - 11 - 9:48
 * @Description: Any Ques or Concerns, plz contact e0838400@u.nus.edu
 */
public interface TransactionService {
    Page<Transaction> getTransactionListSortedByProject(int pageNum, int pageSize, String project, String sort);
}
