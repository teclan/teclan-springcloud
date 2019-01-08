package com.teclan.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "server1")
public interface Server1Service {

    @RequestMapping(method = RequestMethod.GET,
            value = "hello",
            consumes = MediaType.TEXT_PLAIN_VALUE)
    public String sayHello(@RequestParam("name")String name);
}
