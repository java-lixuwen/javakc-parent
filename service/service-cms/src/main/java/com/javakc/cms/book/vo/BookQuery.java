package com.javakc.cms.book.vo;

import lombok.Data;

/**
 * @Progrom: javakc-parent
 * @ClassName: BookQuery
 * @Description: 带条件分页查询的封装类
 * @Outhor: lixuwen
 * @Create: 2022/1/6 14:42
 */
@Data
public class BookQuery {

    private Integer id;
    private String bookName;
    private String author;
    private Integer lianzai;
    private Integer zhuangtai;
    private Integer yuanchuang ;
    private String yijiFenlei ;
    private String erjiFenlei;
    private Integer shoufei;

    private String shouquankaishiDate;
    private String shouquanjieshuDate;


}
