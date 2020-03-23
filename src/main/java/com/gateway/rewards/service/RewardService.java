package com.gateway.rewards.service;

import com.gateway.rewards.entity.Customer;
import com.gateway.rewards.entity.Transaction;
import com.gateway.rewards.repository.CustomerRepository;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RewardService {
  @Autowired
  private CustomerRepository customerRepository;

   public BigDecimal getReward(String firName, LocalDate start, LocalDate end){
       List<Customer> customers = customerRepository.findAllByFirstName(firName);
       Customer customer = customers.get(0);
       BigDecimal spend = customer.getTotalspentThreeMonth(start, end);
       log.info( customer.getFirstName() +" " +customer.getLastName() + " total spent is " + spend + " , in three month from " + start + " to " + end);
       return getReward(spend);
   }

   //A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction
   //(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
   public BigDecimal getReward(BigDecimal totalSpent){
       BigDecimal reward = BigDecimal.valueOf(0.00);
      if(totalSpent.compareTo(BigDecimal.valueOf(0.00)) > 0  ){
          if(totalSpent.compareTo(BigDecimal.valueOf(100))>0){
              reward = reward.add( totalSpent.subtract(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(2)));
              reward = reward.add(BigDecimal.valueOf(50).multiply(BigDecimal.ONE));
          }  else if(totalSpent.compareTo(BigDecimal.valueOf(50)) > 0){
              reward = reward.add( totalSpent.subtract(BigDecimal.valueOf(50)).multiply(BigDecimal.ONE));
          }
      }
      return reward;
   }

}
