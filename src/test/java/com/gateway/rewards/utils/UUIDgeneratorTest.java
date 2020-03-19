package com.gateway.rewards.utils;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UUIDgeneratorTest {


  @Test
  public void generateUUID() {
    for (int i = 0; i < 20; i++) {
      System.out.println(UUIDgenerator.generateRandomUUID());
    }
  }
}
