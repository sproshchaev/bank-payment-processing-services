package com.prosoft.issuingbank.model.dto;

import org.stringtemplate.v4.ST;

public class NewCard {
    private String cardNum;
    private String holderName;

    public NewCard(String cardNum, String holderName) {
        this.cardNum = cardNum;
        this.holderName = holderName;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
}
