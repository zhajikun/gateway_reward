package com.gateway.rewards;

import com.gateway.rewards.entity.Customer;
import com.gateway.rewards.entity.Item;
import com.gateway.rewards.entity.OrderDetail;
import com.gateway.rewards.entity.Transaction;
import com.gateway.rewards.service.RewardService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RewardServiceTest_Mockito {


    @Mock
    Item item;

    RewardService rewardService;

    Customer customer;

    Transaction transaction;

    OrderDetail orderDetail;

    @Before
    public void init() {
        rewardService = new RewardService();
        Mockito.when(item.getItemPrice()).thenReturn(BigDecimal.valueOf(20.00));

        transaction = Transaction.builder().transactionId("TEST Transaction").timestamp(LocalDateTime.now()).build();
        customer = Customer.builder().firstName("FirstName").lastName("LastName")
                .city("Test City").birthday(LocalDate.of(1980, 12, 23))
                .uniqIdentifier("ZZZ-XXX-FFF").phoneNumber("3141234567").state("MO")
                .street("123 test str").socialNumber("1234561234").build();
    }

    //Total spent $20.00 , reward is 0 points
    @Test
    public void testGetReward_20() {

        orderDetail = OrderDetail.builder().item(item).itemQuantity(1).transaction(transaction).build();

        List<OrderDetail> lit = Arrays.asList(orderDetail);

        transaction.setOrderDetails(lit);

        customer.addTransaction(transaction);

        Assert.assertEquals(BigDecimal.valueOf(0.00), rewardService.getReward(customer.getCustomerTotalSpent()));

    }

    //Total spent $120.00 , reward is 90 points
    @Test
    public void testGetReward_120() {

        orderDetail = OrderDetail.builder().item(item).itemQuantity(6).transaction(transaction).build();

        List<OrderDetail> lit = Arrays.asList(orderDetail);

        transaction.setOrderDetails(lit);

        customer.addTransaction(transaction);

        Assert.assertEquals(BigDecimal.valueOf(90.00), rewardService.getReward(customer.getCustomerTotalSpent()));

    }

    //Total spent $80.00 , reward is 30 points
    @Test
    public void testGetReward_80() {

        orderDetail = OrderDetail.builder().item(item).itemQuantity(4).transaction(transaction).build();

        List<OrderDetail> lit = Arrays.asList(orderDetail);

        transaction.setOrderDetails(lit);

        customer.addTransaction(transaction);

        Assert.assertEquals(BigDecimal.valueOf(30.00), rewardService.getReward(customer.getCustomerTotalSpent()));

    }

    //Total spent $0.00 , reward is 0 points
    @Test
    public void testGetReward_0() {
        customer.addTransaction(transaction);
        Assert.assertEquals(BigDecimal.valueOf(0.00), rewardService.getReward(customer.getCustomerTotalSpent()));
    }

}