package com.practice.crud.service;

import com.practice.crud.domain.Member;
import com.practice.crud.domain.Todo;
import com.practice.crud.repository.MemberRepository;
import com.practice.crud.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;

    public MemberService(MemberRepository memberRepository, TodoRepository todoRepository) {
        this.memberRepository = memberRepository;
        this.todoRepository = todoRepository;
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

    public Todo todoSave(Todo todo){

        return todoRepository.save(todo);
    }

    public List<Todo> todoFindByUserId(String userid) {
        List<Todo> list = todoRepository.findByWriter(userid);
        return list;
    }

    public Integer todoUpdate(Todo todo) {
        return todoRepository.todoUpdate(todo);
    }
    public Integer todoDelete(Todo todo) {
        return todoRepository.todoDelete(todo);
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
