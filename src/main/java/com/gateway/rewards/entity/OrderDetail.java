package com.gateway.rewards.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERDETAIL")
public class OrderDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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
  @Override
  public String toString() {
    return "\nOrderDetail{" +
            "item=" + item +
            ", itemQuantity=" + itemQuantity +
            ", orde detail total=" + getOrderDetailTotal() +
            '}'+"\n";
  }
}
