package com.springsecurity.security5_practice2.context.bcm.domain;

import com.springsecurity.security5_practice2.context.auth.domain.AbstractEntity;
import com.springsecurity.security5_practice2.context.auth.domain.AggregateRoot;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class SubMenu extends AbstractEntity implements AggregateRoot {
    private String name;
    private Long topMenuId;
    private Boolean subMenuUsage = true;
    private String url;
    public SubMenu(){

    }

    public SubMenu(String name, Long topMenuId) {
        this.name = name;
        this.topMenuId = topMenuId;
    }
}
