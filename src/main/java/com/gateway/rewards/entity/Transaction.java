package com.gateway.rewards.entity;

import lombok.*;
import org.apache.el.lang.ELArithmetic;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRANSACTION")
public class Transaction {

  @Id
  private String transactionId;

  @Column
  private LocalDateTime timestamp;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "TRANSACTION_ITEM",
      joinColumns = @JoinColumn(name = "TRANSACTION_ID", referencedColumnName="transactionId"),
      inverseJoinColumns = @JoinColumn(name = "ITEM_CATNUMBER", referencedColumnName="CATLOGNUMBER"))
  private List<Item> items;



  @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
  @JoinColumn(name="customer_uniqueidentifier", referencedColumnName = "uniqIdentifier")
  private Customer customer;


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Transaction that  = (Transaction) o;
    return this.transactionId.equalsIgnoreCase(that.transactionId) && this.timestamp.equals(that.timestamp);
  }

  @Override
  public int hashCode() {
    int result = transactionId.hashCode();
    return  31 * result + timestamp.hashCode();
  }

}
