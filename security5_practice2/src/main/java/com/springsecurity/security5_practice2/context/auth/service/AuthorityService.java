package com.springsecurity.security5_practice2.context.auth.service;

import com.springsecurity.security5_practice2.context.auth.domain.Account;
import com.springsecurity.security5_practice2.context.auth.domain.Permission;
import com.springsecurity.security5_practice2.context.auth.domain.Role;
import com.springsecurity.security5_practice2.context.auth.domain.RoleDivision;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AuthorityService extends UserDetailsService {

    Long joinUser(Account member);
    Page<Account> findAllUsers(Pageable pageable);
    List<Role> getRoles(RoleDivision roleDivision);
    void createAccount(Account account);
    void editAccount(Long id, Account account);
    List<Permission> getPermission();
    void editPermission(Long id, Role role);
    void createPermission(Long id, String name);
//    void deletePermission(Long id);
    void deletePers(Long id);
    void createRole(Role role);
    void editRole(Long id, Role role);
    void deleteRole(Long id);
    boolean checkRoles(String roleName);
    void updateAccount(Account account);
    List<Long> getSubMenuByPer();
    User getUserInfo();
}
