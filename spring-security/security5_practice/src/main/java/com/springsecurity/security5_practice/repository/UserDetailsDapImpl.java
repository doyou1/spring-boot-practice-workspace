package com.springsecurity.security5_practice.repository;

import com.springsecurity.security5_practice.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailsDapImpl implements UserDetailsDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUserByUserName(String uname) {
        return sessionFactory.getCurrentSession()
                .get(User.class, uname);
    }

}
