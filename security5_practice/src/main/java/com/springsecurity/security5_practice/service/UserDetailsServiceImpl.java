package com.springsecurity.security5_practice.service;

import com.springsecurity.security5_practice.model.User;
import com.springsecurity.security5_practice.repository.UserDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.security.core.userdetails.User.withUsername;

/**
 * 스프링 시큐리티의 UserDetailsService 인터페이스에는 DB에서 유저 정보를 불러오는
 * 중요한 메소드가 존재하는데 loadUserByUsername() 메소드이다.
 * 이 메소드에서 유저 정보를 불러오는데, UserDetailsService 인터페이스를 구현하면
 * loadUserByUsername() 메소드를 재정의하여 사용자의 정보를 가져온다!
 *
 * @author 재형
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailsDao userDetailsDao;

    @Transactional(readOnly = true)
    @Override
    /**
     * UserDetails : 스프링시큐리티에서 사용자의 정보를 담는 인터페이스
     * UserBuilder의 build 메소드를 통해 생성함
     */
    public UserDetails loadUserByUsername(String uname) throws UsernameNotFoundException {
        User user = userDetailsDao.findUserByUserName(uname);

       org.springframework.security.core.userdetails.User.UserBuilder builder = null;

        if(user != null) {
            // UserBuilder에 DB에서 읽은 정보를 넣고 UserDetails(사용자정보)를 생성함
            builder = withUsername(uname);
            builder.disabled(!user.isEnabled());
            builder.password(user.getPwd());
            String[] authorities = user.getAuth()
                    .stream().map(a -> a.getRole()).toArray(String[]::new);

            // 테이블에서 읽어온 정보를 토대로 UserDetails 인스턴스를 생성
            builder.authorities(authorities);
        } else {
            throw new UsernameNotFoundException("User not found");
        }

        return builder.build();
    }
}
