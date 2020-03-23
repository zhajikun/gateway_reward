# zhajikun / gateway_reward

## About 

* This is a demo springboot application for calculate rewards for customer
* Project status: working/demo
* Jikun Zha Support


## Github download: 
* https://github.com/zhajikun/gateway_reward.git

## How to run

 1. I use liquibase to create a h2 im memory database. After you download my code, you need to run the main class: 
 RewardsApplication.java first to load data to h2 db.
 
 2. With RewardsApplication.java is running, you can run the test cases at RewardsApplicationTests_Integreation.java.
 This is the integreation test which uses the data from h2 db to do the calculation.
 
 3. You can also run the Unit test at RewardServiceTest_Unit.java, which test the reward calculation method "public BigDecimal getReward(BigDecimal totalSpent)" in RewardService class.
 
## Contacts:

###   Jikun Zha
####   email: jzha3@uis.edu
####    cell: 314-255-5888
 
 