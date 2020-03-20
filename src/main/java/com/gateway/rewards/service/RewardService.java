package com.gateway.rewards.service;

import com.gateway.rewards.entity.Customer;
import com.gateway.rewards.entity.Transaction;
import com.gateway.rewards.repository.CustomerRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardService {
  @Autowired
  private CustomerRepository customerRepository;

   public BigDecimal getReward(Customer customer){

     return BigDecimal.valueOf(0L);
   }

   public BigDecimal getTotalSpent(Customer customer){

      Set<Transaction> transactions = customer.getTransactions();
       BigDecimal totalPrice = BigDecimal.ZERO;
       for (Transaction t: customer.getTransactions() ) {

       }

     return BigDecimal.valueOf(0L);
   }

}
