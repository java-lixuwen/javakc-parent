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
    private String bookName;

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
