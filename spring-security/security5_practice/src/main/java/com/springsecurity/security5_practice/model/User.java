package com.springsecurity.security5_practice.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER") // 테이블명
public class User {

    @Id
    @Column(name="UNAME")
    private String uname;

    @Column(name="PWD",nullable=false)
    private String pwd;

    // MySQL
    // @Column(name = "ENABLED", nullable=false)
    //private boolean enabled;

    // MariaDB
    @Type(type = "org.hibername.type.NumericBooleanType")
    @Column(name = "ENABLED", nullable = false, columnDefinition = "TINYINT")
    private boolean enabled;

    // USER와 AUTH는 1:N 관계
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Auth> auth = new HashSet<>();

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Auth> getAuth() {
        return auth;
    }

    public void setAuth(Set<Auth> auth) {
        this.auth = auth;
    }

    @Override
    public String toString() {
        return "User{" +
                "uname='" + uname + '\'' +
                ", pwd='" + pwd + '\'' +
                ", enabled=" + enabled +
                ", auth=" + auth +
                '}';
    }
}
