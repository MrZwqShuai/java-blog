package com.example.ngblog.util;

import com.example.ngblog.entity.Result;

/**
 * Created by 张文强
 * 2018/1/19 10:19
 */
public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setData(object);
        result.setMessage("操作成功");
        return result;
    }

    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }

}
