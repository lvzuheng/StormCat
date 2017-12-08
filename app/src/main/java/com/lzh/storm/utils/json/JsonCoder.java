package com.lzh.storm.utils.json;


import com.alibaba.fastjson.JSONObject;


public class JsonCoder {

	public static <T> T  parseJson(String json,Class<T> tClass){
		T t = JSONObject.parseObject(json,tClass);
		return  t;
	}

	public static Object parseJson(String json,String key){
		return JSONObject.parseObject(json).get(key);
	}

	public static String formatToJson(Object object){
		return JSONObject.toJSON(object).toString();
	}
}
