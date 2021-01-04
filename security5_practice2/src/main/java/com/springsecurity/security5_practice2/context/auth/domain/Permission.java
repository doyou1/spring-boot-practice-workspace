package com.springsecurity.security5_practice2.context.auth.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Entity
public class Permission extends AbstractEntity implements AggregateRoot{

    private String name;

    @Enumerated(EnumType.STRING)
    private PerLevel perLevel;

    private Long resourceId;
    private Boolean perUsage=true;  // Should not use 'usage' as property's name

    public Permission(){
        super();
    }

    public Permission(String name, PerLevel perLevel, Long resourceId) {
        this.name = name;
        this.perLevel = perLevel;
        this.resourceId = resourceId;
    }
}
