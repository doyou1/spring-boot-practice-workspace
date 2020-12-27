package com.practice.crud.repository;

import com.practice.crud.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findByUserid(String userid);
    List<Member> findAll();
}
