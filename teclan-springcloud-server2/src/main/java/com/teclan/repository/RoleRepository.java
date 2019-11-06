package com.teclan.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RoleRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String,Object>> findByUsername(String username){
        List<Map<String,Object>> role = jdbcTemplate.queryForList("select role from user where username=?",username);
        return role;
    }
}
