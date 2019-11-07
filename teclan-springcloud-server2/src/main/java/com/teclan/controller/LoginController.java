package com.teclan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class LoginController {

    @GetMapping("/login")
    public ModelAndView authenticationLogin(ModelAndView mv) throws IOException {
        mv.setViewName("login.html");
        return mv;
    }
}
