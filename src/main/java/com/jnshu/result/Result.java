package com.jnshu.result;

import com.jnshu.pojo.Student;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wyl
 * @version 1.0
 * @date 2020/7/8 19:48
 * @ClassName Result
 */
public class Result {
    public static Map<String, Object> set(int code, String msg, Student student){
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", student);
        return result;
    }

    public static Map<String, Object> set(int code, String msg){
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("msg", msg);
        return result;
    }

}
