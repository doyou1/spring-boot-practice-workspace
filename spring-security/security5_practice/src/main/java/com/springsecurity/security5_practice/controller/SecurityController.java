package com.springsecurity.security5_practice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class SecurityController {

    @GetMapping("/")
    public String main(Model model, Principal principal, Authentication auth) {
        model.addAttribute("message1", "로그인 계정 : " + principal.getName());
        model.addAttribute("message2", "부여받은 롤 : " + auth.getAuthorities());

        return "main";
    }

    @GetMapping("/admin")
    public String goAdmin(Model model, Principal principal, Authentication auth) {
        model.addAttribute("message1", "로그인 계정 : " + principal.getName());
        model.addAttribute("message2", "부여받은 롤 : " + auth.getAuthorities());

        return "admin";
    }
}
