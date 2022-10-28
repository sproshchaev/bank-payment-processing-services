package com.prosoft.issuingbank.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "transaction_type")
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "transaction_type_name")
    private String transactionTypeName;

    public TransactionType() {
    }

    public TransactionType(long id, String transactionTypeName) {
        this.id = id;
        this.transactionTypeName = transactionTypeName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    public void setTransactionTypeName(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionType that = (TransactionType) o;
        return id == that.id && transactionTypeName.equals(that.transactionTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transactionTypeName);
    }

    @Override
    public String toString() {
        return "TransactionType{" +
                "id=" + id +
                ", transactionTypeName='" + transactionTypeName + '\'' +
                '}';
    }
}
