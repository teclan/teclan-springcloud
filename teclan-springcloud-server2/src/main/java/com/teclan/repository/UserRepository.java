package com.teclan.repository;

import com.teclan.security.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String,Object> findByUsername(String username){
        try {
            Map<String, Object> user = jdbcTemplate.queryForMap("SELECT USERNAME,PASSWORD FROM \"USER\" WHERE USERNAME=?", username);
            return user;
        }catch (Exception e){
            LOGGER.error(e.getMessage(),e);
        }
        return null;
    }

}
