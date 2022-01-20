package com.javakc.cms.category.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * 分类管理
 * @Progrom: javakc-parent
 * @ClassName: Category
 * @Description: TODO
 * @Outhor: lixuwen
 * @Create: 2022/1/18 21:48
 */
@Data
@Entity
@Table(name = "cms_category")
public class Category {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "分类管理主键")
    private String id;

    @Column(name = "title")
    @ApiModelProperty(value = "分类名称")
    private String title;

    @Column(name = "parent_id")
    @ApiModelProperty(value = "父类id")
    private String parentId;

}
