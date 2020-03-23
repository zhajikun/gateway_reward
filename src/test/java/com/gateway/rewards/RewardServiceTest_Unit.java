package com.gateway.rewards;

import com.gateway.rewards.service.RewardService;
import lombok.Builder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class RewardServiceTest_Unit {


    RewardService rewardService;

    @Before
    public void init(){
        rewardService = new RewardService();
    }

    @Test
    public void testGetReward() {

        // Transaction total spend is $120.00,  reward points is 90.00
        BigDecimal totalSpent = BigDecimal.valueOf(120.00);
        BigDecimal rewardPoints = rewardService.getReward(totalSpent);
        Assert.assertEquals(BigDecimal.valueOf(90.00),rewardPoints);

        // Transaction total spend is $120.00,  reward points is 40.00
        totalSpent = BigDecimal.valueOf(90.00);
        rewardPoints = rewardService.getReward(totalSpent);
        Assert.assertEquals(BigDecimal.valueOf(40.00), rewardPoints);

        // Transaction total spend is $40.00,  reward points is 0.00
        totalSpent = BigDecimal.valueOf(40.00);
        rewardPoints = rewardService.getReward(totalSpent);
        Assert.assertEquals(BigDecimal.valueOf(0.00), rewardPoints);

        // Transaction total spend is $0.00,  reward points is 0.00
        totalSpent = BigDecimal.valueOf(0.00);
        rewardPoints = rewardService.getReward(totalSpent);
        Assert.assertEquals(BigDecimal.valueOf(0.00), rewardPoints);

    }
}