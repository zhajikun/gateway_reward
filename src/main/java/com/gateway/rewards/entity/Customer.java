package com.gateway.rewards.entity;

import com.gateway.rewards.utils.Util;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Setter(AccessLevel.NONE)
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

    public BigDecimal getTotalspentThreeMonth(LocalDate startDate, LocalDate endDate) {

        Set<Transaction> allTransactions = this.getTransactions();

        Set<Transaction> peridTransactions = new HashSet<>();
        for (Transaction t : allTransactions) {
            LocalDate tranactionDate = Util.getLocalDate(t.getTimestamp());
            if (tranactionDate.isAfter(startDate.minusDays(1L)) && tranactionDate.isBefore(endDate.plusDays(1L))) {
                peridTransactions.add(t);
            }
        }
        return getTotalSpent(peridTransactions);
    }

    private BigDecimal getTotalSpent(Set<Transaction> transactions) {
        BigDecimal totalSpent = new BigDecimal(BigInteger.ZERO);
        if (CollectionUtils.isEmpty(transactions)) return totalSpent;
        for (Transaction t : transactions) {
            totalSpent = totalSpent.add(t.getTranactionTotal());
        }
        return totalSpent;
    }

    public BigDecimal getCustomerTotalSpent() {
        return getTotalSpent(this.getTransactions());
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", uniqIdentifier='" + uniqIdentifier + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", birthday=" + birthday +
                ", socialNumber='" + socialNumber + '\'' +
                ", transactions=" + transactions +
                '}' + "\n";
    }
}
