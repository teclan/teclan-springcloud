package com.teclan.controller;

import com.alibaba.fastjson.JSONObject;
import com.teclan.util.ResultUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: HelloController
 * @Description: TODO
 * @Author: Teclan
 * @Date: 2019/1/7 14:03
 **/
@RestController
public class HelloController {

    @RequestMapping(value = "hello")
    public JSONObject sayHello(HttpServletRequest request, HttpServletResponse response, String name){
        return ResultUtil.get(200," hello "+name);
    }

}
