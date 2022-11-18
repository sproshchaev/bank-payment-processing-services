package com.prosoft.salespoint.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ResponseCode")
public class ResponseCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "error_code")
    private String errorCode;

    @Column(name = "error_description")
    private String errorDescription;

    @Column(name = "error_level")
    private String errorLevel;

    public ResponseCode() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorLevel() {
        return errorLevel;
    }

    public void setErrorLevel(String errorLevel) {
        this.errorLevel = errorLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseCode that = (ResponseCode) o;
        return id == that.id && errorCode.equals(that.errorCode) && errorDescription.equals(that.errorDescription) && errorLevel.equals(that.errorLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, errorCode, errorDescription, errorLevel);
    }

    @Override
    public String toString() {
        return "ResponseCode{" +
                "id=" + id +
                ", errorCode='" + errorCode + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                ", errorLevel='" + errorLevel + '\'' +
                '}';
    }
}
