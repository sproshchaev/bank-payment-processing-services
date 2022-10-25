package com.prosoft.issuingbank.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "account_type")
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "account_type_name")
    private String accountTypeName;

    public AccountType() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountType that = (AccountType) o;
        return id == that.id && accountTypeName.equals(that.accountTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountTypeName);
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "id=" + id +
                ", accountTypeName='" + accountTypeName + '\'' +
                '}';
    }
}
