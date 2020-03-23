package com.gateway.rewards.entity;

import lombok.*;
import org.springframework.util.CollectionUtils;

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
        return transactionId.hashCode();
    }

    @Override
    public String toString() {
        return "\n" + "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", timestamp=" + timestamp +
                ", orderDetails=" + orderDetails +
                '}' + "\n";
    }
}
