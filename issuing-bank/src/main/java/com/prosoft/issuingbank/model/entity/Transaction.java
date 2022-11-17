package com.prosoft.issuingbank.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "sum")
    private double sum;

    @Column(name = "transaction_name")
    private String transactionName;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "sent_to_processing_center")
    private Timestamp sentToProcessingCenter;

    @Column(name = "received_from_processing_center")
    private Timestamp received_from_processing_center;

    public Transaction() {
    }

    public Transaction(Date transactionDate, double sum, String transactionName, TransactionType transactionType,
                       Account account) {
        this.transactionDate = transactionDate;
        this.sum = sum;
        this.transactionName = transactionName;
        this.transactionType = transactionType;
        this.account = account;
    }

    public Transaction(Date transactionDate, double sum, String transactionName, TransactionType transactionType,
                       Account account, Timestamp received_from_processing_center) {
        this.transactionDate = transactionDate;
        this.sum = sum;
        this.transactionName = transactionName;
        this.transactionType = transactionType;
        this.account = account;
        this.received_from_processing_center = received_from_processing_center;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Timestamp getSentToProcessingCenter() {
        return sentToProcessingCenter;
    }

    public void setSentToProcessingCenter(Timestamp sentToProcessingCenter) {
        this.sentToProcessingCenter = sentToProcessingCenter;
    }

    public Timestamp getReceived_from_processing_center() {
        return received_from_processing_center;
    }

    public void setReceived_from_processing_center(Timestamp received_from_processing_center) {
        this.received_from_processing_center = received_from_processing_center;
    }
}
