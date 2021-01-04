package com.skcc.demo.context.auth.domain.authority.role.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.skcc.demo.context.base.domain.AbstractEntity;
import com.skcc.demo.context.base.domain.AggregateRoot;

import lombok.Data;

@Data
@Entity
public class Role extends AbstractEntity implements AggregateRoot{
	
	private String name;
	
	@ElementCollection
	private List<Long> perIdList = new ArrayList<Long>();
	
	private Boolean roleUsage;
	
	@Enumerated(EnumType.STRING)
	private RoleDivision roleDivision;
	
	public Role() {
		
	}
	
	public Role(String name,RoleDivision roleDivision, Boolean roleUsage) {
		this.name= name;
		this.roleDivision = roleDivision;
		this.roleUsage = roleUsage;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getPerIdList() {
		return perIdList;
	}

	public void setPerIdList(List<Long> perIdList) {
		this.perIdList = perIdList;
	}

	public Boolean getRoleUsage() {
		return roleUsage;
	}

	public void setRoleUsage(Boolean roleUsage) {
		this.roleUsage = roleUsage;
	}

	public RoleDivision getRoleDivision() {
		return roleDivision;
	}

	public void setRoleDivision(RoleDivision roleDivision) {
		this.roleDivision = roleDivision;
	}
}
