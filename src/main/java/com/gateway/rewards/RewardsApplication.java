package com.gateway.rewards;


import com.gateway.rewards.entity.Customer;
import com.gateway.rewards.entity.Item;
import com.gateway.rewards.entity.OrderDetail;
import com.gateway.rewards.entity.Transaction;
import com.gateway.rewards.repository.CustomerRepository;
import com.gateway.rewards.repository.ItemRepository;
import com.gateway.rewards.repository.TransactionRepository;
import com.gateway.rewards.utils.UUIDgenerator;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RewardsApplication implements CommandLineRunner {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private ItemRepository itemRepository;

  @Autowired
  private TransactionRepository transactionRepository;

  public static void main(String[] args) {
    SpringApplication.run(RewardsApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    //==================================Customer 1

    Customer customer_1 = Customer.builder().firstName("Mike").lastName("Kim")
        .phoneNumber("314-123-3456")
        .street("123, kings blvd").city("St Louis").state("MO")
        .birthday(LocalDate.of(1983, 11, 6)).socialNumber("1234567890")
        .uniqIdentifier(UUIDgenerator.generateRandomUUID()).build();

    //==================================Customer 2

    Customer customer_2 = Customer.builder().firstName("Jackey").lastName("Wendy")
        .phoneNumber("314-345-9876")
        .street("456 unisersity ct").city("Chesterfield").state("MO")
        .birthday(LocalDate.of(1983, 11, 6)).socialNumber("3216540987")
        .uniqIdentifier(UUIDgenerator.generateRandomUUID()).build();

    //==================================Customer 3
    Customer customer_3 = Customer.builder().firstName("Sohoia").lastName("Dana")
        .phoneNumber("636-368-1098")
        .street("789 abintong st").city("ballwin").state("IL")
        .birthday(LocalDate.of(1995, 07, 16)).socialNumber("0987654321")
        .uniqIdentifier(UUIDgenerator.generateRandomUUID()).build();
    //==============================================================
    Customer saved_1 = customerRepository.save(customer_1);
    customerRepository.save(customer_2);
    customerRepository.save(customer_3);

    //=====================================Item 1
    String item1_uuid = UUIDgenerator.generateRandomUUID();

    Item item_1 = Item.builder().catlogNumber(item1_uuid).itemName("Item-1").itemPrice(
        BigDecimal.valueOf(10.15)).build();

    item1_uuid = UUIDgenerator.generateRandomUUID();
    Item item_2 = Item.builder().catlogNumber(item1_uuid).itemName("Item-2").itemPrice(
        BigDecimal.valueOf(20.27)).build();

    item1_uuid = UUIDgenerator.generateRandomUUID();
    Item item_3 = Item.builder().catlogNumber(item1_uuid).itemName("Item-3").itemPrice(
        BigDecimal.valueOf(30.74)).build();

    item1_uuid = UUIDgenerator.generateRandomUUID();
    Item item_4 = Item.builder().catlogNumber(item1_uuid).itemName("Item-4").itemPrice(
        BigDecimal.valueOf(40.89)).build();

    itemRepository.save(item_1);
    itemRepository.save(item_2);
    itemRepository.save(item_3);
    itemRepository.save(item_4);

    OrderDetail orderDetail_1 = OrderDetail.builder().item(item_1).itemQuantity(3).build();
    OrderDetail orderDetail_2 = OrderDetail.builder().item(item_2).itemQuantity(5).build();
    OrderDetail orderDetail_3 = OrderDetail.builder().item(item_3).itemQuantity(7).build();
    OrderDetail orderDetail_4 = OrderDetail.builder().item(item_4).itemQuantity(9).build();

    List<OrderDetail> orderDetails = Arrays
        .asList(orderDetail_1, orderDetail_2, orderDetail_3, orderDetail_4);

    Transaction transaction_1 = Transaction.builder().timestamp(LocalDateTime.now()).orderDetails(orderDetails)
        .transactionId(UUIDgenerator.generateRandomUUID()).build();

    saved_1.addTransaction(transaction_1);
    customerRepository.save(saved_1);

    orderDetail_1 = OrderDetail.builder().item(item_1).itemQuantity(1).build();
    orderDetail_2 = OrderDetail.builder().item(item_2).itemQuantity(2).build();
    orderDetail_3 = OrderDetail.builder().item(item_3).itemQuantity(3).build();

    orderDetails = Arrays.asList(orderDetail_1, orderDetail_2, orderDetail_3);

    Transaction transaction_2 = Transaction.builder().orderDetails(orderDetails)
        .timestamp(LocalDateTime.now().minusDays(5))
        .transactionId(UUIDgenerator.generateRandomUUID()).build();

    saved_1.addTransaction(transaction_2);

    orderDetail_2 = OrderDetail.builder().item(item_2).itemQuantity(2).build();
    orderDetail_3 = OrderDetail.builder().item(item_3).itemQuantity(4).build();
    orderDetail_4 = OrderDetail.builder().item(item_4).itemQuantity(6).build();

    orderDetails = Arrays.asList(orderDetail_2, orderDetail_3, orderDetail_4);

    Transaction transaction_3 = Transaction.builder().orderDetails(orderDetails)
        .timestamp(LocalDateTime.now().minusMonths(4))
        .transactionId(UUIDgenerator.generateRandomUUID()).build();

    saved_1.addTransaction(transaction_3);
    customerRepository.save(saved_1);
  }
}
