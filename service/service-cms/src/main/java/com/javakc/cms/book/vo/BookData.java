package com.javakc.cms.book.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 设置表头与要添加的属性字段
 * @Progrom: javakc-parent
 * @ClassName: BookData
 * @Description: TODO
 * @Outhor: lixuwen
 * @Create: 2022/1/16 14:43
 */
@Data
public class BookData {

    @ExcelProperty(value = "书名", index = 0)
    private String bookName;

    @ExcelProperty(value = "作者", index = 1)
    private String author;

    @ExcelProperty(value = "一级分类", index = 2)
    private String yijiFenlei;

    @ExcelProperty(value = "二级分类", index = 3)
    private String erjiFenlei;

    @ExcelProperty(value = "是否连载", index = 4)
    private Integer lianzai;

    @ExcelProperty(value = "字数", index = 5)
    private Integer wordNumber;

    @ExcelProperty(value = "是否上线或未上线状态", index = 6)
    private Integer zhuangtai;

    @ExcelProperty(value = "是否付费", index = 7)
    private Integer shoufei;

    @ExcelProperty(value = "授权开始时间", index = 8)
    private Date shouquankaishiTime;

    @ExcelProperty(value = "授权结束时间", index = 9)
    private Date shouquanjieshuTime;

    @ExcelProperty(value = "是否原创", index = 10)
    private Integer yuanchuang;








}
