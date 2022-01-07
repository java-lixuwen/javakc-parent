package com.javakc.commonuitls.api;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class APICODE {

    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 成功或失败
     */
    private Boolean success;
    /**
     * 返回数据
     */
    private Map<String,Object> data = new HashMap<>();

    /**
     *成功之后的方法，返回成功信息
     * @return
     */
    public static APICODE OK(){
        APICODE apicode = new APICODE();
        apicode.setCode(ResultCode.SUCCESS);
        apicode.setMessage("调用成功");
        apicode.setSuccess(true);
        return apicode;
    }

    /**
     * 失败之后的方法，返回失败的信息
     * @return
     */
    public static APICODE ERROR(){
        APICODE apicode = new APICODE();
        apicode.setCode(ResultCode.ERROR);
        apicode.setMessage("调用失败");
        apicode.setSuccess(false);
        return apicode;
    }

    public APICODE success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public APICODE message(String message){
        this.setMessage(message);
        return this;
    }

    public APICODE code(Integer code){
        this.setCode(code);
        return this;
    }

    public APICODE data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public APICODE data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}
