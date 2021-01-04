package com.skcc.demo.context.bcm.domain.functions.menu.model;

import javax.persistence.Entity;

import com.skcc.demo.context.base.domain.AbstractEntity;

import lombok.Data;

@Data
@Entity
public class TopMenu extends AbstractEntity {
	private String name;
	private Boolean topMenuUsage = true;
	private String url;
	public TopMenu() {
		
	}
	public TopMenu(String name) {
		this.name= name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getTopMenuUsage() {
		return topMenuUsage;
	}

	public void setTopMenuUsage(Boolean topMenuUsage) {
		this.topMenuUsage = topMenuUsage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
