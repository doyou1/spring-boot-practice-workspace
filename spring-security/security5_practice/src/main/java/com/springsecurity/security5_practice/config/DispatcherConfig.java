package com.springsecurity.security5_practice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 디스패처 서블릿의 설정파일
 *
 * 스프링 MVC 빈설정, 뷰리졸버 설정
 * 스프링시큐리티에 의해 Default 로그인 페이지로 렌더링될 뷰를 지정
 * 디스패처 서블릿의 설정을 위한 XML파일의 역할을 하는 자바설정 기반의 파일
 *
 * @author 재형
 */
@Configuration
@EnableWebMvc // Spring MVC에서 빈설정을 자동으로 해준다. 필요한 빈 등록
// @Controller, @Service 등에서 빈을 검색할 기본 패키지경로 지정
@ComponentScan(basePackages = {"com.springsecurity.security5_practice"})
public class DispatcherConfig implements WebMvcConfigurer {

    /**
     * JSP ViewResolver 등록
     */
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.
//    }

    /**
     * 스프링 시큐리티에 의해 로그인이 안된 사용자는 home.html에 진입 할 수 없고
     * /login으로 요청을 보내는데, 이때 login.html가 로딩
     * 컨트롤러에서 별도의 요청처리를 하지않고 addViewControllers를 재정의하면 됨
     * 뷰 컨트롤러를 설정함
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
}
