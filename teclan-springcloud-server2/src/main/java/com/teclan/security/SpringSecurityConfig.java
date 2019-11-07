package com.teclan.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/lib/*","/login").permitAll()// 登录页面允许任何人访问
                .antMatchers("/hello").permitAll()// 允许任何人访问
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login") // 设置登录页面
        ;

        // 关闭跨域
        http.csrf().disable();

        // 要想启动Remember-me功能还需要在登录表单中改动
        // <input name="remember-me" type="checkbox">记住我
        http.rememberMe().tokenValiditySeconds(2419200);
    }

    //身份验证管理生成器
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


    }


    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
