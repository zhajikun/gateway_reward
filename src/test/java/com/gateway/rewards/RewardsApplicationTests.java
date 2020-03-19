package com.gateway.rewards;

import com.gateway.rewards.entity.Customer;
import com.gateway.rewards.entity.Item;
import com.gateway.rewards.entity.Transaction;
import com.gateway.rewards.repository.CustomerRepository;
import com.gateway.rewards.repository.ItemRepository;
import com.gateway.rewards.repository.TransactionRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
@Slf4j
public class RewardsApplicationTests {

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  TransactionRepository transactionRepository;

  @Autowired
  ItemRepository itemRepository;

  @Test
  public void contextLoads() {
    List<Customer> customerList = customerRepository.findAll();
    List<Transaction> transactions = transactionRepository.findAll();
    List<Item> items = itemRepository.findAll();

    System.out.println(customerList.size());
    System.out.println(transactions.size());
    System.out.println(items.size());
  }

}
