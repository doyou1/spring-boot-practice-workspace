package com.springsecurity.security5_practice.config;

import java.util.Properties;

import com.springsecurity.security5_practice.model.Auth;
import com.springsecurity.security5_practice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.hibernate.cfg.AvailableSettings.*;

/**
 * 자바설정기반
 * DB연결정보 및 하이버네이트 설정정보 로딩
 * @author 재형
 */
@Configuration
@PropertySource("application.properties")
/*
    @EnableTransactionManagement 와 <tx:annotation-driven/>은
    빈의 @Transactional을 찾아서 트랜잭션이 적용되게 함
*/
@EnableTransactionManagement
@ComponentScan({"com.springsecurity.security5_practice"})
public class AppConfig {
    
    @Autowired
    private Environment env;

    @Bean
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean factoryBean = 
            new LocalSessionFactoryBean();

        Properties props = new Properties();
        
        // MariaDB 연결 정보 설정
        props.put(DRIVER, env.getProperty("spring.datasource.driver"));
        props.put(URL, env.getProperty("spring.datasource.url"));
        props.put(USER, env.getProperty("spring.datasource.username"));
        props.put(PASS, env.getProperty("spring.datasource.password"));

        // 하이버네이트 속성 설정
        props.put(SHOW_SQL, env.getProperty("spring.jpa.properties.hibernate.show_sql"));
        props.put(HBM2DDL_AUTO, env.getProperty("spring.jpa.properties.hibernate.hbm2ddl.auto"));

        // C3P0 속성 설정
        props.put(C3P0_MIN_SIZE, env.getProperty("spring.jpa.properties.hibernate.c3p0.min_size"));
        props.put(C3P0_MAX_SIZE, env.getProperty("spring.jpa.properties.hibernate.c3p0.max_size"));
        props.put(C3P0_ACQUIRE_INCREMENT, env.getProperty("spring.jpa.properties.hibernate.c3p0.acquire_increment"));
        props.put(C3P0_TIMEOUT, env.getProperty("spring.jpa.properties.hibernate.c3p0.timeout"));
        props.put(C3P0_MAX_STATEMENTS, env.getProperty("spring.jpa.properties.hibernate.c3p0.max_statements"));

        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(User.class, Auth.class);

        return factoryBean;
    }

    public HibernateTransactionManager getTransactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());

        return transactionManager;
    }

}
