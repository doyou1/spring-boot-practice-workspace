package com.practice.crud.repository;

import com.practice.crud.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface SDJPAMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

    @Override
    Optional<Member> findById(String id);

}
