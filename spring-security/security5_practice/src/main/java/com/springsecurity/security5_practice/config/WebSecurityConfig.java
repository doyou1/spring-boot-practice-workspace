package com.springsecurity.security5_practice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.accept.ContentNegotiationStrategy;

/**
 * 스프링 시큐리티 설정파일
 * WebSecurityConfigurerAdapter를 상속받고 configure() 메소드를 오버라이드를 재정의
 *
 * @author 재형
 *
 */
/*
-------------------------------------------
    @EnableWebSecurity은 SpringSecurityFilterChain이 자동으로 포함되게 함
    스프링 시큐리티 사용을 위한 어노테이션
-------------------------------------------
*/
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // authorizeRequests : 요청에 따른 권한을 지정
        // hasRole() or hasAnyRole() : 특정 권한을 가지는 사용자만 접근할 수 있음
        // permitAll() or denyAll() : 접근을 전부 허용하거나 제한함

        http.authorizeRequests().antMatchers("/login**").permitAll()
        .and()
        // 스프링 시큐리티의 로그인 처리 모두에게 요청 허용되며
        // /loginAction 요청이며 이는 login화면의 Form태그에 정의됨
        .formLogin().loginPage("/login").loginProcessingUrl("/loginAction").permitAll()
        .and()
        // 로그아웃은 모든 사용자에게 접근이 허용되며 성공시 /login 즉 로그인 페이지로 이동함
        .logout().logoutSuccessUrl("/login").permitAll()
        .and()
        .authorizeRequests().antMatchers("/admin").hasRole("ADMIN")
        .and()
        // 이 예제의 모든 요청은 ADMIN, USER 역할이 있어야 함
        // user2는 ADMIN or USER Role이 없으므로 접근 못함
        .authorizeRequests().anyRequest().hasAnyRole("ADMIN","USER")
        .and()
        // 접근권한이 없어 발생하는 403 ForBidden 오류 처리
        .exceptionHandling()
        .accessDeniedPage("403")
        .and()
        .csrf().disable();
    }
}
