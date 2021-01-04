package com.springsecurity.security5_practice2.context.auth.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Account extends AbstractEntity implements AggregateRoot{

    @NotNull
    private String password;
    private String name;

    @NotNull
    @Email
    private String email;

    private Boolean accountUsage = true;
    private Long roleId;
    private String roleName;

    public Account(){
        super();
    }

    public Account(String password, String name, String email, Boolean accountUsage) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.accountUsage = accountUsage;
    }
}
