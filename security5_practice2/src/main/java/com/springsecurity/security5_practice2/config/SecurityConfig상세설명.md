---
# SecurityConfig 상세 설명
1. @EnableWebSecurity
- @Configuration 클래스에 @EnableWebSecurity 어노테이션을 추가하여 Spring Security 설정할 클래스라고 정의
- 설정은 WebSecurityConfigurerAdapter 클래스를 상속받아 메소드를 구현하는 것이 일반적인 방법
- Web 보안 활성화

2. WebSecurityConfigurerAdapter 클래스
- WebSecurityConfigurer 인스턴스를 편리하게 생성하기 위한 클래스

3. passwordEncoder()
- BCryptPasswordEncoder : Spring Security에서 제공하는 비밀번호 암호화 객체
- Service에서 비밀번호를 암호화할 수 있도록 Bean으로 등록

4. configure() 메소드를 overring해 Security 설정
- configure(WebSecurity web)
    - WebSecurity는 FilterChainProxy를 생성하는 필터
    - web.ignoring().antMatchers("/css/","/js/","/img/","/lib/");
        - 해당 경로의 파일들은 Spring Security가 무시할 수 있도록 설정
        - 즉, 이 파일들은 무조건 통과(보안을 위해 또다른 설정이 필요할 수 있겠다!)
- configure(HttpSecurity http)
    - HttpSecurity를 통해 HTTP요청에 대한 웹 기반 보안을 구성
    - authorizeRequests()
        - HttpServletRequest에 따라 접근(access) 제한
        - antMatchers() 메소드로 특정 경로를 지정하며, permitAll(), hasRole() 메소드로 역할(Role)에 따른 접근 설정
            * Role은 권한을 의미. 즉 어떤 페이지는 관리자만 접근해야 하고, 어떤 페이지는 회원만 접근해야할 때 그 권한을 부여하기 위해 역할을 설정하는 것
            - 예를 들어,
                - .antMatchers("/admin/**").hasRole("SYS_ADMIN")
                    - /admin으로 시작하는 경로는 SYS_ADMIN롤을 가진 사용자만 접근 가능.
                - .antMatchers("/**").permitAll()
                    - 모든 경로에 대해서는 권한없이 접근 가능
                - .anyRequest().authenticated()
                    - 모든 요청에 대해, 인증된 사용자만 접근하도록 설정할 수도 있음
                - .anonymous()
                    - 인증되지 않은 사용자가 접근할 수 있음. (프로젝트엔 적용 X)
    - formLogin()
        - form 기반으로 인증, 로그인 정보는 기본적으로 HttpSession을 이용
        - /login 경로로 접근하면, Spring Security에서 제공하는 로그인 form을 사용할 수 있음
        - .loginPage("/login")
            - 기본 제공되는 form 말고, 커스텀 로그인 폼을 사용하고 싶으면 loginPage() 메소드를 사용
            - 이 때 커스텀 form의 action 경로와 loginPage()의 파라미터 경로가 일치해야 인증을 처리할 수 있음(login.html)
        - .usernameParameter("파라미터명)
            - 로그인 form에서 아이디는 name=username인 input을 기본으로 인식하는데 usernameParameter() 메소드를 통해 파라미터명을 변경할 수 있음.
            > 단, login.html의 id 입력 부분의 name값과 일치시켜줘야함
        - .defaultSuccessUrl("/login/result")
            - 로그인이 성공했을 때 이동되는 페이지이며, 마찬가지로 컨트롤러에서 URL 매핑이 되어 있어야함
    - logout()
        - 로그아웃을 지원하는 메소드이며, WebSecurityConfigurerAdapter를 사용할 때 자동으로 적용
        - 기본적으로 "/logout"에 접근하면 HTTP 세션을 제거
        - .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
            - 로그아웃의 기본 URL(/logout)이 아닌 다른 URL로 재정의
        - .invalidateHttpSession(true)
            - HTTP 세션을 초기화하는 작업
        - deleteCookies("Key명")
            - 로그아웃시, 특정 쿠기를 제거하고 싶을때 사용하는 메소드
    - .exceptionHandling().accessDeniedPage("/user/denied");
        - 예외가 발생했을 때 exceptionHandling() 메소드로 핸들링할 수 있음
        - 프로젝트에서는 접근권한이 없을 때, 로그인 페이지로 이동하도록 명시
    
- configure(AuthenticationManagerBuilder auth)
    - SpringSecurity에서 모든 인증은 AuthenticationManager를 통해 이루어지며
    AuthenticationManager를 생성하기 위해서는 AuthenticationManagerBuilder를 사용
        - 로그인 처리 즉, 인증을 위해서는 UserDetailService를 통해서 필요한 정보들을 가져오는데, 프로젝트에서는 서비스 클래스(authorityService)에서 이를 처리
        - 서비스클래스에서는 UserDetailsService 인터페이스를 implements하여,
          loadUserByUsername() 메소드를 구현해야함
        - 비밀번호 암호화를 위해, passwordEncoder를 사용
    
