package com.prosoft.issuingbank.model.dto;

import java.sql.Date;

public class TransactionCard {
    private Date transactionDate;
    private double sum;
    private String transactionName;
    private String transactionTypeName;
    private String accountNumber;
    private long issuingBankIdTransaction;

    public TransactionCard() {
    }

    public TransactionCard(Date transactionDate, double sum, String transactionName, String transactionTypeName,
                           String accountNumber, long issuingBankIdTransaction) {
        this.transactionDate = transactionDate;
        this.sum = sum;
        this.transactionName = transactionName;
        this.transactionTypeName = transactionTypeName;
        this.accountNumber = accountNumber;
        this.issuingBankIdTransaction = issuingBankIdTransaction;
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

    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    public void setTransactionTypeName(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getIssuingBankIdTransaction() {
        return issuingBankIdTransaction;
    }

    public void setIssuingBankIdTransaction(long issuingBankIdTransaction) {
        this.issuingBankIdTransaction = issuingBankIdTransaction;
    }

    @Override
    public String toString() {
        return "TransactionCard{" +
                "transactionDate=" + transactionDate +
                ", sum=" + sum +
                ", transactionName='" + transactionName + '\'' +
                ", transactionTypeName='" + transactionTypeName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", issuingBankIdTransaction=" + issuingBankIdTransaction +
                '}';
    }
}
