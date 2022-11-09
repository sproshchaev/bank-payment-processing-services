package com.prosoft.processingcenter.model.entity;

import javax.persistence.*;
import java.sql.Date;

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
    private Double sum; // todo Double -> double

    @Column(name = "transaction_name")
    private String transactionName;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "terminal_id")
    private Terminal terminal;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "response_code_id")
    private ResponseCode responseCode;

    @Column(name = "authorization_code")
    private String authorizationCode;

    @Column(name = "received_from_issuing_bank")
    private Date receivedFromIssuingBank;

    @Column(name = "sent_to_issuing_bank")
    private Date sentToIssuingBank;

    public Transaction() {
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

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public Date getReceivedFromIssuingBank() {
        return receivedFromIssuingBank;
    }

    public void setReceivedFromIssuingBank(Date receivedFromIssuingBank) {
        this.receivedFromIssuingBank = receivedFromIssuingBank;
    }

    public Date getSentToIssuingBank() {
        return sentToIssuingBank;
    }

    public void setSentToIssuingBank(Date sentToIssuingBank) {
        this.sentToIssuingBank = sentToIssuingBank;
    }
}
