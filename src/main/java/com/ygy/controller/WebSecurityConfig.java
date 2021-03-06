package com.ygy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ygy
 * @date 2019/4/30 17:17
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers( "/selectOrderAll","/selectOrder1","/ttt","/home","/signIn","/addMenu/*","/images/pic1.jpg","/updatemenu1", "/register","/selectByrid","/images/pic2.jpg","/wxLogin*","/ygy","/selectOrder","/submission","/selectOrder/select","/hello","/login/upload","/selectmenuPc","/deletemenu").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable() //关闭CSRF
                .formLogin()
                .loginPage("/home")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
}
