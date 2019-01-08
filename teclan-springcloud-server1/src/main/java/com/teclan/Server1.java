package com.teclan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: Server1
 * @Description: TODO
 * @Author: Teclan
 * @Date: 2019/1/7 14:06
 **/


@EnableDiscoveryClient
@SpringBootApplication
public class Server1 {
    public static void main(String[] args) {
        SpringApplication.run(Server1.class, args);
    }
}