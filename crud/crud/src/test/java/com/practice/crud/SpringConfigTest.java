package com.practice.crud;

import com.practice.crud.repository.BoardRepository;
import com.practice.crud.repository.MemberRepository;
import com.practice.crud.service.BoardService;
import com.practice.crud.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfigTest {

    MemberRepository memberRepository;
    BoardRepository boardRepository;

    @Autowired
    public SpringConfigTest(MemberRepository memberRepository, BoardRepository boardRepository) {
        this.memberRepository = memberRepository;
        this.boardRepository = boardRepository;
    }

    public SpringConfigTest() {
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

