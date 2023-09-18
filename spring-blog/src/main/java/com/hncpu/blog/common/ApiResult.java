package com.hncpu.blog.common;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

@Data
public class ApiResult<T> {

    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    public static <T>ApiResult<T> success(T object) {
        ApiResult<T> r = new ApiResult<T>();
        r.data = object;
        r.code = 20000;
        return r;
    }
    public static <T>ApiResult<T> msg(String msg){
        ApiResult<T> r= new ApiResult<T>();
        r.msg=msg;
        r.code = 20000;
        return r;
    }
    public static <T>ApiResult<T> error(String msg) {
        ApiResult r = new ApiResult();
        r.msg = msg;
        r.code = 20001;
        return r;
    }

    public ApiResult<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }

}
