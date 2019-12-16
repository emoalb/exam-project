package com.exam.examproject.config;

import com.exam.examproject.domain.entities.User;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class SpringSecurityTestConfig {
    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        User basicUser = new User("emo", "password", "user@company.com");
        UserActive basicActiveUser = new UserActive(basicUser, Arrays.asList(
                new SimpleGrantedAuthority("ROLE_USER"),
                new SimpleGrantedAuthority("PERM_FOO_READ")
        ));

      //  User managerUser = new User("emo12", "password", "user@company.com");
//        UserActive managerActiveUser = new UserActive(managerUser, Arrays.asList(
//                new SimpleGrantedAuthority("ROLE_MANAGER"),
//                new SimpleGrantedAuthority("PERM_FOO_READ"),
//                new SimpleGrantedAuthority("PERM_FOO_WRITE"),
//                new SimpleGrantedAuthority("PERM_FOO_MANAGE")
//        ));

        return new InMemoryUserDetailsManager(
                basicActiveUser
        );
    }
}
