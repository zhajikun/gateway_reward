package com.gateway.rewards.repository;

import com.gateway.rewards.entity.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  List<Customer> findAllByFirstName(String firstName);

  List<Customer> findAllByLastName(String lastName);

  Customer findByUniqIdentifier(String uniqId);
}
