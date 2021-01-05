package com.practice.crud.controller;

import com.practice.crud.domain.Member;
import com.practice.crud.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final MemberService memberService;

    @Autowired
    public HomeController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        if(session.getAttribute("userid") != null) {
            model.addAttribute("todoList", memberService.todoFindByUserId((String)session.getAttribute("userid")));
            return "home";
        }
        return "member/notLogin";
    }
}
