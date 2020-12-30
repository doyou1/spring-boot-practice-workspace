package com.practice.crud.controller;

import com.practice.crud.domain.Member;
import com.practice.crud.domain.Todo;
import com.practice.crud.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Optional;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("login")
    public String login(Member member, HttpSession session) {
        Optional<Member> dbMember = memberService.findByUserid(member.getUserid());

        try{
            if(dbMember.isPresent()){
                if(member.getUserid().equals(dbMember.get().getUserid())
                        && member.getPassword().equals(dbMember.get().getPassword())){
                    session.setAttribute("userid",member.getUserid());
                }
                else {
                    throw new IllegalStateException("아이디나 비밀번호가 틀립니다.");
                }
            }
            else {
                throw new IllegalStateException("아이디나 비밀번호가 틀립니다.");
            }
        } catch(IllegalStateException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.setAttribute("userid",null);

        return "redirect:/";
    }

    @GetMapping("join")
    public String join() {

        return "member/join";
    }

    @PostMapping("join")
    public String join(Member member, String passCheck, HttpSession session) {
        if(member.getPassword().equals(passCheck)) {
            memberService.join(member);
        }
        return "redirect:/";
    }

    @PostMapping("todo/save")
    @ResponseBody
    public Todo todoSave(String data, HttpSession session) {
        Todo todo = new Todo();
        todo.setText(data);
        todo.setWriter((String)session.getAttribute("userid"));
        todo.setDate(LocalDate.now());

        return memberService.todoSave(todo);
    }
    @PostMapping("todo/update")
    @ResponseBody
    public Integer todoUpdate(Todo todo, HttpSession session) {
        todo.setWriter((String) session.getAttribute("userid"));

        return memberService.todoUpdate(todo);
    }
    @PostMapping("todo/delete")
    @ResponseBody
    public Integer todoDelete(Todo todo, HttpSession session) {
        todo.setWriter((String) session.getAttribute("userid"));

        return memberService.todoDelete(todo);
    }
}
