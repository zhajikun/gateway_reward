package com.gateway.rewards.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ITEM")
public class Item {

  @Id
  private String catlogNumber;

  @Column
  private String itemName;

  @Column
  private BigDecimal itemPrice;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Item that = (Item) o;

    return this.catlogNumber.equalsIgnoreCase(that.catlogNumber);

  }

  @Override
  public int hashCode() {
    return catlogNumber.hashCode();
  }
}
