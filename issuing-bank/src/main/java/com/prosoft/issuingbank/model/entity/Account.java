package com.prosoft.issuingbank.model.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "balance")
    private double balance;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER) // тянем для транзакции (n+1)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type_id")
    private AccountType accountType;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "account_opening_date")
    private Date accountOpeningDate;

    @Column(name = "suspending_operations")
    private boolean suspendingOperations;

    @Column(name = "account_closing_date")
    private Date accountClosingDate;

    public Account() {
    }

    public Account(double balance, Currency currency, AccountType accountType, Client client, Date accountOpeningDate,
                   boolean suspendingOperations) {
        this.balance = balance;
        this.currency = currency;
        this.accountType = accountType;
        this.client = client;
        this.accountOpeningDate = accountOpeningDate;
        this.suspendingOperations = suspendingOperations;
    }

    public Account(String accountNumber, double balance, Currency currency, AccountType accountType, Client client,
                   Date accountOpeningDate, boolean suspendingOperations) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
        this.accountType = accountType;
        this.client = client;
        this.accountOpeningDate = accountOpeningDate;
        this.suspendingOperations = suspendingOperations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Date getAccountOpeningDate() {
        return accountOpeningDate;
    }

    public void setAccountOpeningDate(Date accountOpeningDate) {
        this.accountOpeningDate = accountOpeningDate;
    }

    public boolean isSuspendingOperations() {
        return suspendingOperations;
    }

    public void setSuspendingOperations(boolean suspendingOperations) {
        this.suspendingOperations = suspendingOperations;
    }

    public Date getAccountClosingDate() {
        return accountClosingDate;
    }

    public void setAccountClosingDate(Date accountClosingDate) {
        this.accountClosingDate = accountClosingDate;
    }
}
