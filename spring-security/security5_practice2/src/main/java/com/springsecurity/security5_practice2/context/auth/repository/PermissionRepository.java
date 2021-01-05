package com.springsecurity.security5_practice2.context.auth.repository;

import com.springsecurity.security5_practice2.context.auth.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PermissionRepository extends QuerydslPredicateExecutor<Permission>, JpaRepository<Permission, Long>, PagingAndSortingRepository<Permission, Long> {

    Permission findByName(String string);
    List<Permission> findByResourceId(Long id);
}
