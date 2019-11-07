package com.teclan.security;

import com.teclan.controller.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);



    @Autowired
    private UserDetailsService userDetailsService;

    public CustomAuthenticationProvider() {
        super();
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        if(auth==null){
            return null;
        }

        String username = String.valueOf(auth.getName());
        String password = String.valueOf(auth.getCredentials().toString());

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (password.equals(userDetails.getPassword())) {
                final UserDetails principal = new User(username, password, userDetails.getAuthorities());

                final Authentication authentication = new UsernamePasswordAuthenticationToken(principal, password, userDetails.getAuthorities());

                return authentication;
            }else{
                LOGGER.warn("密码不一致,password:{},real:{}",password,userDetails.getPassword());
                throw new Exception("密码错误，登录失败");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

}