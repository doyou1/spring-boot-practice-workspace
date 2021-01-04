package com.springsecurity.security5_practice2.context.auth.service;

import com.springsecurity.security5_practice2.context.auth.domain.Account;
import com.springsecurity.security5_practice2.context.auth.domain.Permission;
import com.springsecurity.security5_practice2.context.auth.domain.Role;
import com.springsecurity.security5_practice2.context.auth.domain.RoleDivision;
import com.springsecurity.security5_practice2.context.auth.repository.AccountRepository;
import com.springsecurity.security5_practice2.context.auth.repository.PermissionRepository;
import com.springsecurity.security5_practice2.context.auth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;


    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(userEmail).orElseThrow(() -> new UsernameNotFoundException(userEmail+"이 존재하지 않습니다."));

        String roleName = roleRepository.findById(account.getRoleId()).get().getRoleDivision().getValue();

        return new User(account.getEmail(), account.getPassword(), getAuthorities("ROLE_"+roleName));   // 반드시 "ROLE_"로 시작해야 함
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String roleName) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(roleName));

        return authorities;
    }

    @Override
    public Long joinUser(Account member) {
        return null;
    }

    @Override
    public Page<Account> findAllUsers(Pageable pageable) {
        return null;
    }

    @Override
    public List<Role> getRoles(RoleDivision roleDivision) {
        return null;
    }

    @Override
    public void createAccount(Account account) {

    }

    @Override
    public void editAccount(Long id, Account account) {

    }

    @Override
    public List<Permission> getPermission() {
        return null;
    }

    @Override
    public void editPermission(Long id, Role role) {

    }

    @Override
    public void createPermission(Long id, String name) {

    }

    @Override
    public void deletePers(Long id) {

    }

    @Override
    public void createRole(Role role) {

    }

    @Override
    public void editRole(Long id, Role role) {

    }

    @Override
    public void deleteRole(Long id) {

    }

    @Override
    public boolean checkRoles(String roleName) {
        return false;
    }

    @Override
    public void updateAccount(Account account) {

    }

    @Override
    public List<Long> getSubMenuByPer() {
        return null;
    }

    @Override
    public User getUserInfo() {
        return null;
    }
}
