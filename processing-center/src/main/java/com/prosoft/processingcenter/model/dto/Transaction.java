package com.prosoft.processingcenter.model.dto;

import com.prosoft.processingcenter.model.entity.TransactionType;

public class Transaction {
    private String transaction_date;
    private Double sum;
    private String transactionName;
    private TransactionType transactionType;
    private String cardNumber;

    public Transaction() {
    }

    public Transaction(String transaction_date, Double sum, String transactionName, TransactionType transactionType,
                       String cardNumber) {
        this.transaction_date = transaction_date;
        this.sum = sum;
        this.transactionName = transactionName;
        this.transactionType = transactionType;
        this.cardNumber = cardNumber;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transaction_date='" + transaction_date + '\'' +
                ", sum=" + sum +
                ", transactionName='" + transactionName + '\'' +
                ", transactionType=" + transactionType +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
