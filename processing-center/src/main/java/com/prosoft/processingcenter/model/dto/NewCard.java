package com.prosoft.processingcenter.model.dto;

// todo не используется - удалить!
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

    @Override
    public String toString() {
        return "NewCard{" +
                "cardNum='" + cardNum + '\'' +
                ", holderName='" + holderName + '\'' +
                '}';
    }
}
