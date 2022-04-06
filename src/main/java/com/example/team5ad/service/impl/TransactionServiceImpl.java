package com.example.team5ad.service.impl;

import com.example.team5ad.entity.PrivateProperty;
import com.example.team5ad.entity.Transaction;
import com.example.team5ad.repo.PrivatePropertyDAO;
import com.example.team5ad.repo.TransactionDAO;
import com.example.team5ad.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * @Author: Xin Yuchen
 * @Date: 2022/2/11 - 02 - 11 - 9:51
 * @Description: Any Ques or Concerns, plz contact e0838400@u.nus.edu
 */
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionDAO tDAO;
    @Autowired
    PrivatePropertyDAO pDAO;
    @Override
    public Page<Transaction> getTransactionListSortedByProject(int pageNum, int pageSize, String project, String sort) {
        Transaction transaction = new Transaction();
        PrivateProperty privateProperty = pDAO.findPrivatePropertyByProject(project);
        transaction.setPrivateProperty(privateProperty); // here we got the example
        ExampleMatcher projectMatcher = ExampleMatcher.matching()
                .withMatcher("project",ExampleMatcher.GenericPropertyMatchers.exact());
        Example<Transaction> example = Example.of(transaction,projectMatcher);
        PageRequest pageRequest;
        if(sort.equals("all")){
            pageRequest = PageRequest.of(pageNum,pageSize);
        } else {
            pageRequest = PageRequest.of(pageNum,pageSize,Sort.by(sort));
        }

        Page<Transaction> transactions = tDAO.findAll(example,pageRequest);
        return transactions;
    }
}
