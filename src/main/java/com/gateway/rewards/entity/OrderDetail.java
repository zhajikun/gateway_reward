package com.gateway.rewards.entity;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERDETAIL")
public class OrderDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  private long id;

  @OneToOne
  @JoinColumn(name = "ITEMCATNUM", referencedColumnName = "catlogNumber")
  private Item item;

  @Column
  private int itemQuantity;

  @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
  @JoinColumn(name = "TRANSACTIONID")
  private Transaction transaction;

  public BigDecimal getOrderDetailTotal() {
    return this.item.getItemPrice().multiply(BigDecimal.valueOf(itemQuantity));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderDetail that = (OrderDetail) o;
    return this.item.equals(that.item);
  }

  @Override
  public int hashCode() {
    return this.item.hashCode();
  }
}
