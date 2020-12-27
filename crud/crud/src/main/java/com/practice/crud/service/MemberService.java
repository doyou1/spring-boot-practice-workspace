package com.practice.crud.service;

import com.practice.crud.domain.Member;
import com.practice.crud.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param : 유저 회원가입 정보
     */
    public long join(Member member) {
        // DB에 같은 ID 있는지 확인
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getNum();
    }

    public Member findById(String id) {
        Optional<Member> member = memberRepository.findById(id);

        return member.get();
    }
    /**
     * 중복 확인 method
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findById(member.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 ID입니다.");
                });

    }
}
