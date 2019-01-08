package com.teclan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: GoodByeController
 * @Description: TODO
 * @Author: Teclan
 * @Date: 2019/1/7 17:43
 **/
@RestController
public class GoodByeController {

    @Autowired
    private Server1Service server1Service;

    @RequestMapping(value = "sayGoodbye")
    public  String  sayGoodbyte(String name){
        return server1Service.sayHello(name)+"\n\n"+"goodbyte "+name;
    }
}
