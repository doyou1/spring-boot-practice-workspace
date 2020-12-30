package com.practice.crud;

import com.practice.crud.domain.Board;
import com.practice.crud.repository.BoardRepository;
import com.practice.crud.repository.MemberRepository;
import com.practice.crud.repository.TodoRepository;
import com.practice.crud.service.BoardService;
import com.practice.crud.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final TodoRepository todoRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository, BoardRepository boardRepository, TodoRepository todoRepository) {
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
        this.todoRepository = todoRepository;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository, todoRepository);
    }
    @Bean
    public BoardService boardService() {
        return new BoardService(boardRepository);
    }

}
