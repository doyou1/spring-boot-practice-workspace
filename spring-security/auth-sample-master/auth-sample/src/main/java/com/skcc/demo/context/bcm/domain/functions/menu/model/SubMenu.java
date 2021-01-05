package com.skcc.demo.context.bcm.domain.functions.menu.model;

import javax.persistence.Entity;

import com.skcc.demo.context.base.domain.AbstractEntity;
import com.skcc.demo.context.base.domain.AggregateRoot;

import lombok.Data;

@Data
@Entity
public class SubMenu extends AbstractEntity implements AggregateRoot{
	private String name;
	private Long topMenuId;
	private Boolean subMenuUsage = true;
	private String url;
	public SubMenu() {
		
	}
	public SubMenu(String name, Long topMenuId) {
		this.name = name;
		this.topMenuId = topMenuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTopMenuId() {
		return topMenuId;
	}

	public void setTopMenuId(Long topMenuId) {
		this.topMenuId = topMenuId;
	}

	public Boolean getSubMenuUsage() {
		return subMenuUsage;
	}

	public void setSubMenuUsage(Boolean subMenuUsage) {
		this.subMenuUsage = subMenuUsage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
