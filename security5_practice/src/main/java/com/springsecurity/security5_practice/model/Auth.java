package com.springsecurity.security5_practice.model;

import javax.persistence.*;

@Entity
@Table(name="AUTH") // 테이블명
public class Auth {

    @Id
    @Column(name = "ROLE")
    private String role;

    @ManyToOne
    @JoinColumn(name = "UNAME")
    private User user;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "role='" + role + '\'' +
                ", user=" + user +
                '}';
    }
}
