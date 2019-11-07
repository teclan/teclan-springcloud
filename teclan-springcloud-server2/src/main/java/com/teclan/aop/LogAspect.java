package com.teclan.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.teclan.log.LogService;
import com.teclan.model.Log;
import com.teclan.util.HttpTool;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

@Aspect
@Component
public class LogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private LogService logService;

    //配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
    // 要求该包下，参数依次是 HttpServletRequest，HttpServletResponse，返回值必须是 json格式，不满足上述要求的，下面的拦截将会报错
    @Pointcut("execution(* com.teclan.controller..*(..))")
    public void aspect() {
    }

    //配置环绕通知,使用在方法aspect()上注册的切入点
    @Around("aspect()")
    public void around(JoinPoint joinPoint) {
        try {

            Object[] args = joinPoint.getArgs();

            HttpServletRequest httpServletRequest = (HttpServletRequest) args[0];
            HttpServletResponse httpServletResponse = (HttpServletResponse) args[1];

            HttpServletResponseWrapper response = new HttpServletResponseWrapper(httpServletResponse);

            Object result = ((ProceedingJoinPoint) joinPoint).proceed();

            Log log = Log.parse(httpServletRequest);

            log.setResult(JSON.toJSONString(result));
            log.setStatus(((JSONObject) result).getString("code"));
            HttpTool.setResponse(response, 200, (JSONObject) result);

            logService.create(log);

            LOGGER.info("操作日志:{}", log);
        } catch (Throwable e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


}
