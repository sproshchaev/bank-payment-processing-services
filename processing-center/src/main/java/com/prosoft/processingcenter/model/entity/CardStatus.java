package com.prosoft.processingcenter.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "card_status")
public class CardStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "card_status_name")
    private String cardStatusName;

    public CardStatus() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardStatusName() {
        return cardStatusName;
    }

    public void setCardStatusName(String cardStatusName) {
        this.cardStatusName = cardStatusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardStatus that = (CardStatus) o;
        return id == that.id && cardStatusName.equals(that.cardStatusName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardStatusName);
    }

    @Override
    public String toString() {
        return "CardStatus{" +
                "id=" + id +
                ", cardStatusName='" + cardStatusName + '\'' +
                '}';
    }
}
