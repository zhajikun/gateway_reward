package com.gateway.rewards.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;


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

  @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  private List<OrderDetail> orderDetails;

  @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
  @JoinColumn(name = "customer_uniqueidentifier", referencedColumnName = "uniqIdentifier")
  private Customer customer;

  public BigDecimal getTranactionTotal() {
    BigDecimal transactionTotal = BigDecimal.ZERO;
    if (!CollectionUtils.isEmpty(orderDetails)) {

      for (OrderDetail orderDetail : this.orderDetails) {
        transactionTotal = transactionTotal.add(orderDetail.getOrderDetailTotal());
      }
    }
    return transactionTotal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Transaction that = (Transaction) o;
    return this.transactionId.equalsIgnoreCase(that.transactionId) && this.timestamp
        .equals(that.timestamp);
  }

  @Override
  public int hashCode() {
    int result = transactionId.hashCode();
    return 31 * result + timestamp.hashCode();
  }

}
