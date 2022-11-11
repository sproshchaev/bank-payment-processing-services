package com.prosoft.issuingbank.model.dto;

import java.sql.Date;

public class Card {

    private String cardNumber;

    private Date expirationDate;

    private String holderName;

    private String cardStatusName;

    private String paymentSystemName;

    private String accountNumber;

    private double balance;

    private String currencyLetterCode;

    private long issuingBankId;

    public Card() {
    }

    public Card(String cardNumber, Date expirationDate, String holderName, String cardStatusName,
                String paymentSystemName, String accountNumber, double balance, String currencyLetterCode,
                long issuingBankId) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.holderName = holderName;
        this.cardStatusName = cardStatusName;
        this.paymentSystemName = paymentSystemName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currencyLetterCode = currencyLetterCode;
        this.issuingBankId = issuingBankId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getCardStatusName() {
        return cardStatusName;
    }

    public void setCardStatusName(String cardStatusName) {
        this.cardStatusName = cardStatusName;
    }

    public String getPaymentSystemName() {
        return paymentSystemName;
    }

    public void setPaymentSystemName(String paymentSystemName) {
        this.paymentSystemName = paymentSystemName;
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

    public String getCurrencyLetterCode() {
        return currencyLetterCode;
    }

    public void setCurrencyLetterCode(String currencyLetterCode) {
        this.currencyLetterCode = currencyLetterCode;
    }

    public long getIssuingBankId() {
        return issuingBankId;
    }

    public void setIssuingBankId(long issuingBankId) {
        this.issuingBankId = issuingBankId;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + cardNumber + '\'' +
                ", expirationDate=" + expirationDate +
                ", holderName='" + holderName + '\'' +
                ", cardStatusName='" + cardStatusName + '\'' +
                ", paymentSystemName='" + paymentSystemName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                ", currencyLetterCode='" + currencyLetterCode + '\'' +
                ", issuing_bank_id=" + issuingBankId +
                '}';
    }
}
