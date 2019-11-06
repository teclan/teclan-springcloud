package com.teclan.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {


    private @Value("${datasource.driver-class-name}")
    String driver;
    private @Value("${datasource.url}")
    String url;
    private @Value("${datasource.username}")
    String username;
    private @Value("${datasource.password}")
    String password;

    @Bean(name = "dataSource")
    public DataSource getDataSource() {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        return new HikariDataSource(config);
    }


    @Bean(name = "jdbcTemplate")
    public JdbcTemplate getJdbcTemplate() {

        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        return new JdbcTemplate(new HikariDataSource(config));
    }
}
