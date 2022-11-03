package com.prosoft.processingcenter.model.dto;

public class Payment {
    private long id;
    private String terminalId;
    private String transactionDate;
    private String cardNumber;
    private String expiryDate;
    private String sum;
    private String currencyLetterCode;
    private String errorCode;
    private String authorizationCode;

    public Payment() {
    }

    public Payment(String terminalId, String transactionDate, String cardNumber, String expiryDate, String sum,
                   String currencyLetterCode) {
        this.terminalId = terminalId;
        this.transactionDate = transactionDate;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.sum = sum;
        this.currencyLetterCode = currencyLetterCode;
    }

    public Payment(long id, String terminalId, String transactionDate, String cardNumber, String expiryDate, String sum, String currencyLetterCode, String errorCode, String authorizationCode) {
        this.id = id;
        this.terminalId = terminalId;
        this.transactionDate = transactionDate;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.sum = sum;
        this.currencyLetterCode = currencyLetterCode;
        this.errorCode = errorCode;
        this.authorizationCode = authorizationCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getCurrencyLetterCode() {
        return currencyLetterCode;
    }

    public void setCurrencyLetterCode(String currencyLetterCode) {
        this.currencyLetterCode = currencyLetterCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", terminalId='" + terminalId + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", sum='" + sum + '\'' +
                ", currencyLetterCode='" + currencyLetterCode + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", authorizationCode='" + authorizationCode + '\'' +
                '}';
    }
}
