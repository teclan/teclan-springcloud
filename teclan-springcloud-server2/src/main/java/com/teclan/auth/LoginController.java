package com.teclan.auth;

import com.alibaba.fastjson.JSONObject;
import com.teclan.util.HttpTool;
import com.teclan.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public ModelAndView authenticationLogin(HttpServletRequest request, HttpServletResponse response,ModelAndView mv,HttpSession session) throws IOException {

        if (session != null) {
            SavedRequest savedRequest = (SavedRequest)session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
            if (savedRequest != null) {
                mv.addObject("redirectUrl", savedRequest.getRedirectUrl());
            }
        }
        mv.setViewName("login.html");
        return mv;
    }


    @PostMapping("/auth")
    @ResponseBody // 不加这个注解，将会导致回环 URL
    public JSONObject authenticate(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws IOException {

        JSONObject jsonObject = HttpTool.readJSONParam(request);
        String username = jsonObject.getString("account");
        String password = jsonObject.getString("password");

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        try {
            //使用SpringSecurity拦截登陆请求 进行认证和授权
            Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            if(authenticate==null){
                return ResultUtil.get(500, "授权失败");
            }

            SecurityContextHolder.getContext().setAuthentication(authenticate);
            //使用redis session共享
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext()); // 这个非常重要，否则验证后将无法登陆
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            return ResultUtil.get(500, "授权失败",e.getMessage());
        }

        String redirectUrl=null;
        if (session!=null && session.getAttribute("SPRING_SECURITY_SAVED_REQUEST")!=null){
            SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
            redirectUrl = savedRequest.getRedirectUrl();
        }

//        if(StringUtils.isNotEmpty(redirectUrl)){
//            response.sendRedirect(redirectUrl);
//        }

        return ResultUtil.getwithRedirectUrl(200, "认证成功",redirectUrl);
    }
}
