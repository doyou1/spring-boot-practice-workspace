package com.practice.crud;

import com.practice.crud.domain.Board;
import com.practice.crud.repository.BoardRepository;
import com.practice.crud.repository.MemberRepository;
import com.practice.crud.service.BoardService;
import com.practice.crud.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository, BoardRepository boardRepository) {
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
    @Bean
    public BoardService boardService() {
        return new BoardService(boardRepository);
    }

}
