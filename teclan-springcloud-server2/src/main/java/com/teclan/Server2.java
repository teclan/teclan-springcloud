package com.teclan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName: Server1
 * @Description: TODO
 * @Author: Teclan
 * @Date: 2019/1/7 14:06
 **/


@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class Server2 {
    public static void main(String[] args) {
        SpringApplication.run(Server2.class, args);
    }
}