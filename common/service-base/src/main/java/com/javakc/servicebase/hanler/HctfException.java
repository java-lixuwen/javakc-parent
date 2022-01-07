package com.javakc.servicebase.hanler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HctfException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "信息")
    private String msg;


}
