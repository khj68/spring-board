package com.jun.tacocloud.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
            .antMatchers("/design", "/orders")
            .hasRole("ROLE_USER")
            .antMatchers("/", "/**").permitAll();
    }

    @Autowired
    DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // IN MEMORY
        // auth.inMemoryAuthentication()
        //         .withUser("user1")
        //         .password("{noop}password1")
        //         .authorities("ROLE_USER")
        //         .and()
        //         .withUser("user2")
        //         .password("{noop}password2")
        //         .authorities("ROLE_USER");

        // JDBC
        // auth
        //     .jdbcAuthentication()
        //     .dataSource(dataSource)
        //     .usersByUsernameQuery(
        //         "select username, password, enabled from users where username=?"
        //     ).authoritiesByUsernameQuery(
        //         "select username, authority from authorities where username=?"
        //     ).passwordEncoder(new NoEncodingPasswordEncoder());

        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(encoder());

    }
}