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
    //==============================================================Customer 4
    Customer customer_4 = Customer.builder().firstName("Hui").lastName("Wu")
            .phoneNumber("636-123-9834")
            .street("908 Univ st").city("Chesterfield").state("MO")
            .birthday(LocalDate.of(1975, 02, 14)).socialNumber("0981235678")
            .uniqIdentifier(UUIDgenerator.generateRandomUUID()).build();

    //==============================================================Customer 4
    Customer customer_5 = Customer.builder().firstName("Jikun").lastName("Zha")
            .phoneNumber("314-111-2222")
            .street("908 Univ st").city("Chesterfield").state("MO")
            .birthday(LocalDate.of(1989, 12, 24)).socialNumber("9081234567")
            .uniqIdentifier(UUIDgenerator.generateRandomUUID()).build();

    Customer saved_1 = customerRepository.save(customer_1);
    Customer saved_2 =  customerRepository.save(customer_2);
    Customer saved_3 =  customerRepository.save(customer_3);
    Customer saved_4 =  customerRepository.save(customer_4);
    Customer saved_5 =  customerRepository.save(customer_5);


    //=====================================Item 1 - 6


    Item item_1 = Item.builder().catlogNumber(UUIDgenerator.generateRandomUUID()).itemName("Item-1").itemPrice(
        BigDecimal.valueOf(10.15)).build();

    Item item_2 = Item.builder().catlogNumber(UUIDgenerator.generateRandomUUID()).itemName("Item-2").itemPrice(
        BigDecimal.valueOf(20.27)).build();

    Item item_3 = Item.builder().catlogNumber(UUIDgenerator.generateRandomUUID()).itemName("Item-3").itemPrice(
        BigDecimal.valueOf(30.74)).build();

    Item item_4 = Item.builder().catlogNumber(UUIDgenerator.generateRandomUUID()).itemName("Item-4").itemPrice(
            BigDecimal.valueOf(40.89)).build();

    Item item_5 = Item.builder().catlogNumber(UUIDgenerator.generateRandomUUID()).itemName("Item-5").itemPrice(
            BigDecimal.valueOf(50.00)).build();

    Item item_6 = Item.builder().catlogNumber(UUIDgenerator.generateRandomUUID()).itemName("Item-6").itemPrice(
            BigDecimal.valueOf(20.00)).build();

    itemRepository.save(item_1);
    itemRepository.save(item_2);
    itemRepository.save(item_3);
    itemRepository.save(item_4);
    itemRepository.save(item_5);
    itemRepository.save(item_6);
  //==================================================Transaction 1

    Transaction transaction_1 = Transaction.builder().timestamp(LocalDateTime.now())
        .transactionId(UUIDgenerator.generateRandomUUID()).build();

    OrderDetail orderDetail_1 = OrderDetail.builder().item(item_1).itemQuantity(3).transaction(transaction_1).build();
    OrderDetail orderDetail_2 = OrderDetail.builder().item(item_2).itemQuantity(5).transaction(transaction_1).build();
    OrderDetail orderDetail_3 = OrderDetail.builder().item(item_3).itemQuantity(7).transaction(transaction_1).build();
    OrderDetail orderDetail_4 = OrderDetail.builder().item(item_4).itemQuantity(9).transaction(transaction_1).build();

    List<OrderDetail> orderDetails = Arrays
            .asList(orderDetail_1, orderDetail_2, orderDetail_3, orderDetail_4);

    transaction_1.setOrderDetails(orderDetails);

    saved_1.addTransaction(transaction_1);

    customerRepository.save(saved_1);


    //==================================================Transaction 2
    Transaction transaction_2 = Transaction.builder().timestamp(LocalDateTime.now().minusDays(5))
        .transactionId(UUIDgenerator.generateRandomUUID()).build();

    orderDetail_1 = OrderDetail.builder().item(item_1).itemQuantity(1).transaction(transaction_2).build();
    orderDetail_2 = OrderDetail.builder().item(item_2).itemQuantity(2).transaction(transaction_2).build();
    orderDetail_3 = OrderDetail.builder().item(item_3).itemQuantity(3).transaction(transaction_2).build();

    orderDetails = Arrays.asList(orderDetail_1, orderDetail_2, orderDetail_3);

    transaction_2.setOrderDetails(orderDetails);

    saved_1.addTransaction(transaction_2);

    //==================================================Transaction 3

    Transaction transaction_3 = Transaction.builder().timestamp(LocalDateTime.now().minusMonths(4))
        .transactionId(UUIDgenerator.generateRandomUUID()).build();

    orderDetail_2 = OrderDetail.builder().item(item_2).itemQuantity(2).transaction(transaction_3).build();
    orderDetail_3 = OrderDetail.builder().item(item_3).itemQuantity(4).transaction(transaction_3).build();
    orderDetail_4 = OrderDetail.builder().item(item_4).itemQuantity(6).transaction(transaction_3).build();

    orderDetails = Arrays.asList(orderDetail_2, orderDetail_3, orderDetail_4);
    transaction_3.setOrderDetails(orderDetails);
    saved_1.addTransaction(transaction_3);

    customerRepository.save(saved_1);

    //=======================================================================Transaction 4  $120.00
    Transaction transaction_4 = Transaction.builder().timestamp(LocalDateTime.now())
            .transactionId(UUIDgenerator.generateRandomUUID()).build();

    OrderDetail orderDetail_5 = OrderDetail.builder().item(item_5).itemQuantity(2).transaction(transaction_4).build();
    OrderDetail orderDetail_6 = OrderDetail.builder().item(item_6).itemQuantity(1).transaction(transaction_4).build();
    orderDetails = Arrays.asList(orderDetail_5, orderDetail_6);
    transaction_4.setOrderDetails(orderDetails);
    saved_2.addTransaction(transaction_4);
    customerRepository.save(saved_2);
    //=======================================================================Transaction 5 $70.00
    Transaction transaction_5 = Transaction.builder().timestamp(LocalDateTime.now())
            .transactionId(UUIDgenerator.generateRandomUUID()).build();
    OrderDetail orderDetail_7= OrderDetail.builder().item(item_5).itemQuantity(1).transaction(transaction_5).build();
    OrderDetail orderDetail_8 = OrderDetail.builder().item(item_6).itemQuantity(1).transaction(transaction_5).build();

    orderDetails = Arrays.asList(orderDetail_7, orderDetail_8);

    transaction_5.setOrderDetails(orderDetails);

    saved_3.addTransaction(transaction_5);
    customerRepository.save(saved_3);

    //=======================================================================Transaction 6 $20.00
    Transaction transaction_6 = Transaction.builder().timestamp(LocalDateTime.now())
            .transactionId(UUIDgenerator.generateRandomUUID()).build();
    OrderDetail orderDetail_9 = OrderDetail.builder().item(item_6).itemQuantity(1).transaction(transaction_6).build();
    orderDetails = Arrays.asList(orderDetail_9);
    transaction_6.setOrderDetails(orderDetails);
    saved_5.addTransaction(transaction_6);
    customerRepository.save(saved_5);
  }
}
