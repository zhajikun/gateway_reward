package com.gateway.rewards.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Util {

    public static LocalDate getLocalDate(LocalDateTime localDateTime){
      return LocalDate.of(localDateTime.getYear(),localDateTime.getMonth(), localDateTime.getDayOfMonth());
    }


}
