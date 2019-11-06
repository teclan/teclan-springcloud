package com.teclan.security;

import com.teclan.model.User;
import com.teclan.repository.RoleRepository;
import com.teclan.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Map<String,Object> user = userRepository.findByUsername(name);
        if (user==null){
            throw new AuthenticationCredentialsNotFoundException("authError");
        }
        List<Map<String,Object>> role = roleRepository.findByUsername(name);
        LOGGER.info("用户 {},权限:{}",user,role);

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        role.forEach(r -> authorities.addAll(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_"+r.get("role"))));
        return new org.springframework.security.core.userdetails.User(user.get("username").toString(),user.get("password").toString(),authorities);
    }
}
