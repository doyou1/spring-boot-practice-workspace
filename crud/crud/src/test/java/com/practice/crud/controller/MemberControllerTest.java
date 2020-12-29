package com.practice.crud.controller;


import com.practice.crud.domain.Member;
import com.practice.crud.repository.MemberRepository;
import com.practice.crud.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class MemberControllerTest {

    @Autowired
    MemberService memberService;

    @Autowired
    public MemberControllerTest(MemberRepository memberRepository) {
        memberService = new MemberService(memberRepository);
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setPassword("test");
        member.setUserid("test");
        System.out.println(member);
        // when
        Long id = memberService.join(member);
        System.out.println("id : " + id);
        // then
        Optional<Member> result = memberService.findByUserid("test");

        System.out.println(result.toString());
    }
}
