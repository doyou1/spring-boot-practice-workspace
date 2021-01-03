package com.springsecurity.security5_practice.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * web.xml에 대응되는 자바기반 설정파일 생성
 * 
 * DispatcherServlet과 애플리케이션의 서블릿 컨텍스트내의 스프링 애플리케이션 컨텍스트를 설정
 * 
 * AbstractAnnotationConfigDispatcherServletInitializer는 두개의 자바빈 관리자를 생성하는데
 * DispatcherServlet(자식 컨텍스트)과 ContextLoaderListener(부모 컨텐스트)를 생성
 * 
 * DispatcherServlet은 컨트롤러, 뷰 리졸버, 핸들러 매핑과 같은 웹 컴포넌트가 포함된 빈을 로딩
 * 
 * ContextLoaderListener는 애플리케이션 내의 그 외의 다른 빈을 로딩 또는 DispatcherServlet이 여럿인 경우 한번에 설정을 로딩하기 위해 사용
 */
public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Root Web Application Context에 등록될 빈들
     * Load database and Spring Security Configuration
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class, WebSecurityConfig.class};
    }

    /**
     * DispatcherServlet Context에 등록될 빈들
     * Load Spring Web Configuration
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{DispatcherConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
