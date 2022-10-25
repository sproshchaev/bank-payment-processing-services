package com.prosoft.issuingbank.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bank_setting")
public class BankSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "setting")
    private String setting;

    @Column(name = "current_value")
    private String currentValue;

    @Column(name = "description")
    private String description;

    public BankSetting() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(String currentValue) {
        this.currentValue = currentValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankSetting that = (BankSetting) o;
        return id == that.id && setting.equals(that.setting) && currentValue.equals(that.currentValue) && description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, setting, currentValue, description);
    }

    @Override
    public String toString() {
        return "BankSetting{" +
                "id=" + id +
                ", setting='" + setting + '\'' +
                ", currentValue='" + currentValue + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
