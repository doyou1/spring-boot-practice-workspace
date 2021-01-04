package com.springsecurity.security5_practice2.context.bcm.repository;

import com.springsecurity.security5_practice2.context.bcm.domain.SubMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface SubMenuRepository extends QuerydslPredicateExecutor<SubMenu>, JpaRepository<SubMenu, Long>, PagingAndSortingRepository<SubMenu, Long> {

    List<SubMenu> findByTopMenuId(Long topMenuId);
}
