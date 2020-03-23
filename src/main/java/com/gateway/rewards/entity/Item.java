package com.gateway.rewards.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

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

    @Override
    public String toString() {
        return "Item{" +
                "catlogNumber='" + catlogNumber + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                '}';
    }
}
