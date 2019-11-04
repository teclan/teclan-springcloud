package com.teclan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ByeController
 * @Description: TODO
 * @Author: Teclan
 * @Date: 2019/1/7 17:43
 **/
@RestController
public class ByeController {

    @Autowired
    private Server1Service server1Service;

    @RequestMapping(value = "bye")
    public  String  saybye(String name){
        return server1Service.sayHello(name)+"\n\n"+"goodbye "+name;
    }
}
