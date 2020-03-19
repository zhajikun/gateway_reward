package com.gateway.rewards.repository;

import com.gateway.rewards.entity.Transaction;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

  //List<Transaction> findByCustomerName(String firstName, String lastName);
}
