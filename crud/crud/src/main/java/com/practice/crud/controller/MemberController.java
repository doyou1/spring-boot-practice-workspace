package com.practice.crud.controller;

import com.practice.crud.domain.Member;
import com.practice.crud.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("login")
    public String login(Member member, HttpSession session) {
        Member dbMember = memberService.findById(member.getId());

        if(member.getId().equals(dbMember.getId())
            && member.getPassword().equals(dbMember.getPassword())){
            session.setAttribute("id",member.getId());
        }
        else {
            throw new IllegalStateException("아이디나 비밀번호가 틀립니다.");
        }

        return "redirect:/";
    }

    @GetMapping("join")
    public String join() {

        return "member/join";
    }

    @PostMapping("join")
    public String join(Member member, String passCheck, HttpSession session) {
        System.out.println(member.toString());
        System.out.println(passCheck);
        return "redirect:/";
    }

}
