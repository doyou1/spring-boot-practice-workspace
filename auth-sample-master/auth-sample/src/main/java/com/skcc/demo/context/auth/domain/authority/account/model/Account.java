package com.skcc.demo.context.auth.domain.authority.account.model;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.skcc.demo.context.base.domain.AbstractEntity;
import com.skcc.demo.context.base.domain.AggregateRoot;

import lombok.Data;

@Data
@Entity
public class Account extends AbstractEntity implements AggregateRoot {
	@NotNull
	private String password;

	private String name;

	@NotNull
	@Email
	private String email;

	private Boolean accountUsage = true;
	private Long roleId;
	private String roleName;

	public Account() {

	}

	public Account(String name, String password, String email, Boolean accountUsage) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.accountUsage = accountUsage;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAccountUsage() {
		return accountUsage;
	}

	public void setAccountUsage(Boolean accountUsage) {
		this.accountUsage = accountUsage;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
