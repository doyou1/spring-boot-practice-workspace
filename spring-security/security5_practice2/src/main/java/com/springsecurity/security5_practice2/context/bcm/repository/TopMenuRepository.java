package com.springsecurity.security5_practice2.context.bcm.repository;

import com.springsecurity.security5_practice2.context.bcm.domain.TopMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TopMenuRepository extends QuerydslPredicateExecutor<TopMenu>, JpaRepository<TopMenu, Long>, PagingAndSortingRepository<TopMenu, Long> {
}
