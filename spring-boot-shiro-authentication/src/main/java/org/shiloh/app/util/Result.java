package org.shiloh.app.util;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shiloh
 * @Date Created in 2019/12/26 16:05
 * @description 统一响应数据
 */
public class Result extends HashMap<String, Object> {

    private static final long serialVersionUID = 8127338163330601950L;

    public Result() {
        this.put("code", 0);
        this.put("msg", "success");
        this.put("timestamp", System.currentTimeMillis());
    }

    public static Result ok() {
        return new Result();
    }

    public static Result ok(String msg) {
        Result result = new Result();
        result.put("msg", msg);
        return result;
    }

    public static Result ok(Map<String, Object> params) {
        Result result = new Result();
        result.putAll(params);
        return result;
    }

    public static Result error(int code, String msg) {
        Result result = new Result();
        result.put("code", code);
        result.put("msg", msg);
        return result;
    }

    public static Result error(String msg) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
    }

    public static Result error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "未知异常，请联系管理员");
    }

    @Override
    public Result put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
