package com.springsecurity.security5_practice2.context.auth.domain;

import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Role extends AbstractEntity implements AggregateRoot{

    private String name;

    @ElementCollection
    private List<Long> perIdList = new ArrayList<>();

    private Boolean roleUsage;

    @Enumerated(EnumType.STRING)
    private RoleDivision roleDivision;

    public Role(){
        super();
    }

    public Role(String name, Boolean roleUsage, RoleDivision roleDivision) {
        this.name = name;
        this.roleUsage = roleUsage;
        this.roleDivision = roleDivision;
    }
}
