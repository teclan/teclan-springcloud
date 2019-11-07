package com.teclan.log;

import com.alibaba.fastjson.JSONObject;
import com.teclan.aop.LogAspect;
import com.teclan.model.Log;
import com.teclan.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogService.class);


    public JSONObject create(Log log) {
        JSONObject json = null;
        try {
            json = JSONObject.parseObject(JSONObject.toJSONString(log));

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResultUtil.get(200, "推送失败", e.getMessage());
        }
        return create(json);
    }


    public JSONObject create(JSONObject jsonObject) {
        LOGGER.info("{}",jsonObject);
        return ResultUtil.get(200, "推送成功");
    }

}
