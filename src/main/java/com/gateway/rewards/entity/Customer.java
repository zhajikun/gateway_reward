package com.gateway.rewards.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

  private static final long serialVersionUID = 4596586012515910980L;

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private long id;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private String phoneNumber;

  @Column
  private String street;

  @Column
  private String city;

  @Column
  private String state;

  @Column
  private LocalDate birthday;

  @Column
  private String uniqIdentifier;

  @Column
  private String socialNumber;


  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch=FetchType.LAZY,orphanRemoval = true)
  private Set<Transaction> transactions;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Customer that = (Customer) o;
    return this.uniqIdentifier.equalsIgnoreCase(that.uniqIdentifier) || this.id == that.id;
  }

  @Override
  public int hashCode() {
    return uniqIdentifier.hashCode();
  }


  public void addTransaction(Transaction transaction) {
    if (null == this.transactions) {
      this.transactions = new HashSet<>();
    }

    transaction.setCustomer(this);
    this.transactions.add(transaction);
  }


}
