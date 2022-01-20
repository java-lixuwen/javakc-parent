package com.javakc.cms.category.vo;

import lombok.Data;

import java.util.List;

/**
 * 一级分类
 * @Progrom: javakc-parent
 * @ClassName: YijiFenlei
 * @Description: TODO
 * @Outhor: lixuwen
 * @Create: 2022/1/18 22:55
 */
@Data
public class YijiFenlei {
    private String id;
    private String title;
    private List<ErjiFenlei> erjiFenleiList;
}
