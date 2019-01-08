package com.teclan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: HelloController
 * @Description: TODO
 * @Author: Teclan
 * @Date: 2019/1/7 14:03
 **/
@RestController
public class HelloController {

    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public String sayHello(String name){
        return " hello "+name;
    }

}
