package com.javakc.servicebase.hanler;

import com.javakc.oss.commonutils.api.APICODE;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public APICODE errorHandler(Exception e){
        e.printStackTrace();
        return APICODE.ERROR().message("服务器异常");
    }
    /**
     * 自定义异常格式
     */
    @ExceptionHandler(HctfException.class)
    @ResponseBody
    public APICODE errorHandler(HctfException e){
        e.printStackTrace();
        return APICODE.ERROR().code(e.getCode()).message(e.getMsg());
    }
}
