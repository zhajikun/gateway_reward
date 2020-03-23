package com.gateway.rewards;

import com.gateway.rewards.entity.Customer;
import com.gateway.rewards.entity.Item;
import com.gateway.rewards.entity.Transaction;
import com.gateway.rewards.repository.CustomerRepository;
import com.gateway.rewards.repository.ItemRepository;
import com.gateway.rewards.repository.TransactionRepository;
import com.gateway.rewards.service.RewardService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
@Slf4j
public class RewardsApplicationTests_Integreation {

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  TransactionRepository transactionRepository;

  @Autowired
  ItemRepository itemRepository;

  @Autowired
  RewardService rewardService;

  @Test
  public void preloadDBdata() {
    List<Customer> customerList = customerRepository.findAll();
    List<Transaction> transactions = transactionRepository.findAll();
    List<Item> items = itemRepository.findAll();

    Assert.assertEquals(3, customerList.size());
    Assert.assertEquals(3, transactions.size());
    Assert.assertEquals(4, items.size());

    System.out.println("Total customers are: "+customerList.size());
    System.out.println("Total transactions are:  " + transactions.size());
    System.out.println("Total items are: "+ items.size());
  }

  @Test
  public void testCustomerRepository(){
    String firstName = "Mike";
    String lastName = "Kim";
    List<Customer> customers = customerRepository.findAllByFirstName(firstName);
    List<Customer> customers_2 = customerRepository.findAllByLastName(lastName);
    Assert.assertFalse(customers.isEmpty());
    Assert.assertFalse(customers_2.isEmpty());

    Customer customer = customers.get(0);

    BigDecimal totalSpent = customer.getCustomerTotalSpent();

    System.out.println(customer.getFirstName() +" "+customer.getLastName()+ "total spend money is "+ totalSpent);

    Set<Transaction> transactions = customer.getTransactions();

    for(Transaction t : transactions){
      System.out.println("Transction : " +t.getTransactionId() +", transaction date is " +t.getTimestamp() + ", total price is:" + t.getTranactionTotal());
    }

  }


  @Test
  public void testTestTranactionThreeMonth(){
    String firstName = "Mike";
    List<Customer> customers = customerRepository.findAllByFirstName(firstName);
    Customer customer = customers.get(0);
    LocalDate start = LocalDate.of(2020, 01, 1);
    LocalDate end = LocalDate.of(2020, 03, 31);
    BigDecimal spend = customer.getTotalspentThreeMonth(start, end);
    System.out.println("Mike spent in 01-01-2020 -- 03-31-2020, in 3 month: " + spend);
  }

    @Test
    public void testTestTranactionThreeMonthListDetail(){
        String firstName = "Mike";
        List<Customer> customers = customerRepository.findAllByFirstName(firstName);
        Customer customer = customers.get(0);
        LocalDate start = LocalDate.of(2020, 01, 1);
        LocalDate end = LocalDate.of(2020, 03, 31);
        BigDecimal spend = customer.getTotalspentThreeMonth(start, end);
        System.out.println("Custmer:" + firstName + " details:  "+ customer.toString());
        System.out.println(firstName + " spent in 01-01-2020 -- 03-31-2020, in 3 month: " + spend);
    }


    //Jack spent $120, get 90.00 points
  @Test
  public void testTestTranactionThreeMonthListDetail_2(){
    String firstName = "Jackey";
    List<Customer> customers = customerRepository.findAllByFirstName(firstName);
    Customer customer = customers.get(0);
    LocalDate start = LocalDate.of(2020, 01, 1);
    LocalDate end = LocalDate.of(2020, 03, 31);
    BigDecimal spend = customer.getTotalspentThreeMonth(start, end);
    System.out.println("Custmer: "+ customer);
    System.out.println( firstName+ " spent in 01-01-2020 -- 03-31-2020, in 3 month: " + spend);
    BigDecimal reward = rewardService.getReward(firstName, start, end);
    System.out.println(firstName +" reward is " + reward);
    Assert.assertEquals(BigDecimal.valueOf(90.00), reward);
  }


  //Sohoia spent $70, get 20.00 points
  @Test
  public void testTestTranactionThreeMonthListDetail_3(){
    String firstName = "Sohoia";
    List<Customer> customers = customerRepository.findAllByFirstName(firstName);
    Customer customer = customers.get(0);
    LocalDate start = LocalDate.of(2020, 01, 1);
    LocalDate end = LocalDate.of(2020, 03, 31);
    BigDecimal spend = customer.getTotalspentThreeMonth(start, end);
    System.out.println("Custmer: "+ customer);
    System.out.println( firstName+ " spent in 01-01-2020 -- 03-31-2020, in 3 month: " + spend);
    BigDecimal reward = rewardService.getReward(firstName, start, end);
    System.out.println(firstName +" reward is " + reward);
    Assert.assertEquals(BigDecimal.valueOf(20.00), reward);
  }

  //Hui spent $0, get 0.00 points
  @Test
  public void testTestTranactionThreeMonthListDetail_4(){
    String firstName = "Hui";
    List<Customer> customers = customerRepository.findAllByFirstName(firstName);
    Customer customer = customers.get(0);
    LocalDate start = LocalDate.of(2020, 01, 1);
    LocalDate end = LocalDate.of(2020, 03, 31);
    BigDecimal spend = customer.getTotalspentThreeMonth(start, end);
    System.out.println("Custmer: "+ customer);
    System.out.println( firstName+ " spent in 01-01-2020 -- 03-31-2020, in 3 month: " + spend);
    BigDecimal reward = rewardService.getReward(firstName, start, end);
    System.out.println(firstName +" reward is " + reward);
    Assert.assertEquals(BigDecimal.valueOf(0.00), reward);
  }

  //Jikun spent $40.00, get 0.00 points
  @Test
  public void testTestTranactionThreeMonthListDetail_5(){
    String firstName = "Jikun";
    List<Customer> customers = customerRepository.findAllByFirstName(firstName);
    Customer customer = customers.get(0);
    LocalDate start = LocalDate.of(2020, 01, 1);
    LocalDate end = LocalDate.of(2020, 03, 31);
    BigDecimal spend = customer.getTotalspentThreeMonth(start, end);
    System.out.println("Custmer: "+ customer);
    System.out.println( firstName+ " spent in 01-01-2020 -- 03-31-2020, in 3 month: " + spend);
    BigDecimal reward = rewardService.getReward(firstName, start, end);
    System.out.println(firstName +" reward is " + reward);
    Assert.assertEquals(BigDecimal.valueOf(0.00), reward);
  }
}
