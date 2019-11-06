package com.teclan.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String,Object> findByUsername(String username){
        Map<String,Object> user = jdbcTemplate.queryForMap("select id,username,password from user where username=?",username);
        return user;
    }

}
