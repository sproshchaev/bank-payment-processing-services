package com.prosoft.salespoint.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_access")
public class UserAccess {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "user_login")
    private String userLogin;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "user_role")
    private String userRole;

    public UserAccess() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccess that = (UserAccess) o;
        return id == that.id && userLogin.equals(that.userLogin) && userPassword.equals(that.userPassword) && fullName.equals(that.fullName) && userRole.equals(that.userRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userLogin, userPassword, fullName, userRole);
    }

    @Override
    public String toString() {
        return "UserAccess{" +
                "id=" + id +
                ", userLogin='" + userLogin + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", fullName='" + fullName + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
