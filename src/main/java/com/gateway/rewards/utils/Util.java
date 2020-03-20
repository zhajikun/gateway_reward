package com.gateway.rewards.utils;

import com.gateway.rewards.entity.Item;
import com.gateway.rewards.entity.Transaction;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Util {

    public static LocalDate getLocalDate(LocalDateTime localDateTime){
      return LocalDate.of(localDateTime.getYear(),localDateTime.getMonth(), localDateTime.getDayOfMonth());
    }

    public static BigDecimal getTransactionTotal(Transaction transaction){
        BigDecimal totalSpent = new BigDecimal(BigInteger.ZERO);
        if(CollectionUtils.isEmpty(transaction.getItems())) return totalSpent;

        List<Item> items= transaction.getItems();
        for(Item item : items){
            totalSpent = totalSpent.add(item.getItemPrice());
        }
        return totalSpent;
    }
}
