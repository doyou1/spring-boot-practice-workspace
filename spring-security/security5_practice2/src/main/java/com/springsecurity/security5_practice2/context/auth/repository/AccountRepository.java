package com.springsecurity.security5_practice2.context.auth.repository;

import com.springsecurity.security5_practice2.context.auth.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface AccountRepository extends QuerydslPredicateExecutor<Account>, JpaRepository<Account, Long>, PagingAndSortingRepository<Account, Long> {

    Optional<Account> findByEmail(String email);
    List<Account> findByRoldId(Long id);

}
