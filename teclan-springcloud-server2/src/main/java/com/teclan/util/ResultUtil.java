package com.teclan.util;

import com.alibaba.fastjson.JSONObject;

public class ResultUtil {

	public static JSONObject get(int code, String message) {
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("code", code);
		jsonResult.put("message", message);
		return jsonResult;
	}

	public static JSONObject getwithRedirectUrl(int code, String message,String redirectUrl) {
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("code", code);
		jsonResult.put("message", message);
		jsonResult.put("redirectUrl", redirectUrl);
		return jsonResult;
	}

	public static JSONObject get(int code, String message, Object data) {
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("code", code);
		jsonResult.put("message", message);
		jsonResult.put("data", data);
		return jsonResult;
	}

	public static JSONObject get(int code, String message, Object data,Object pageInfo) {
		JSONObject jsonResult = new JSONObject();
		jsonResult.put("code", code);
		jsonResult.put("message", message);
		jsonResult.put("data", data);
		jsonResult.put("pageInfo", pageInfo);
		return jsonResult;
	}
	
}
