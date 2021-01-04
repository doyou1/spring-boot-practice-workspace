package com.springsecurity.security5_practice2.context.auth.repository;

import com.springsecurity.security5_practice2.context.auth.domain.Role;
import com.springsecurity.security5_practice2.context.auth.domain.RoleDivision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface RoleRepository extends QuerydslPredicateExecutor<Role>, JpaRepository<Role, Long>, PagingAndSortingRepository<Role, Long> {

    List<Role> findAllByRoleDivision(RoleDivision roleDivision);
    Role findByName(String name);

}
