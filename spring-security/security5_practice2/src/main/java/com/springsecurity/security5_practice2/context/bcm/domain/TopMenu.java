package com.springsecurity.security5_practice2.context.bcm.domain;

import com.springsecurity.security5_practice2.context.auth.domain.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class TopMenu extends AbstractEntity {
    private String name;
    private Boolean topMenuUsage = true;
    private String url;
    public TopMenu(){
        super();
    }
    public TopMenu(String name) {
        this.name = name;
    }
}
