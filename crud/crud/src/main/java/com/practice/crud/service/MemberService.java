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

        member = memberRepository.save(member);

        return member.getId();
    }

    public Optional<Member> findByUserid(String userid) {
        Optional<Member> member = memberRepository.findByUserid(userid);
        return member.isPresent() ? member : Optional.empty();
    }
    /**
     * 중복 확인 method
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByUserid(member.getUserid())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 ID입니다.");
                });

    }
}
