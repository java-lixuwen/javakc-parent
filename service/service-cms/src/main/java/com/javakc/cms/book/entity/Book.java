package com.javakc.cms.book.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "cms_book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "书籍主键")
    private Integer id;


    @Column(name = "book_name")
    @ApiModelProperty(value = "书籍名称")
    private String bookName ;

    @Column(name = "author")
    @ApiModelProperty(value = "作者")
    private String author ;

    @Column(name = "yiji_fenlei")
    @ApiModelProperty(value = "一级分类 1是  0否")
    private String yijiFenlei ;

    @Column(name = "erji_fenlei")
    @ApiModelProperty(value = "二级分类 1是 0否")
    private String erjiFenlei;

    @Column(name = "is_lianzai")
    @ApiModelProperty(value = "是否连载 1是 0否 ")
    private int lianzai;

    @Column(name = "word_number")
    @ApiModelProperty(value = "总字数")
    private int wordNumber;

    @Column(name = "is_zhuangtai")
    @ApiModelProperty(value = "状态 1上线  0未上线")
    private int zhuangtai;

    @Column(name = "is_shoufei")
    @ApiModelProperty(value = "全本收费 1收费 0免费")
    private int shoufei;

    @Column(name = "shouquankaishi_time")
    @ApiModelProperty(value = "授权开始时间")
    private Date shouquankaishiTime;

    @Column(name = "shouquanjieshu_time")
    @ApiModelProperty(value = "授权结束时间")
    private Date shouquanjieshuTime ;

    @Column(name = "yuanchuang")
    @ApiModelProperty(value = "原创 1是 0否")
    private int yuanchuang ;

    @Column(name = "image_url")
    @ApiModelProperty(value = "书封")
    private String imageUrl ;

    @Column(name = "jianjie")
    @ApiModelProperty(value = "简介")
    private String jianjie ;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    @Column(name = "chuangjian_time",nullable = false,updatable = false)
    @ApiModelProperty(value = "创建时间")
    private Date chaungjianTime;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT-8")
    @Column(name="gengxin_time",nullable = false,insertable = false)
    @ApiModelProperty(value = "更新时间")
    private Date gengxinTime;

}
