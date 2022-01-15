package com.javakc.conpyriht.banquanyinjin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.internal.$Gson$Preconditions;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @Progrom: javakc-parent
 * @ClassName: Banquanyinjin
 * @Description: TODO
 * @Outhor: lixuwen
 * @Create: 2022/1/14 22:02
 */
@Getter
@Setter
@Entity
@Table(name = "conpyriht_banquanyinjin")
@EntityListeners(AuditingEntityListener.class)
public class Banquanyinjin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "版权ID")
    @Column(name = "banquan_id")
    private Integer banduanId;

    @Column(name = "banquan_name")
    @ApiModelProperty(value = "版权名称")
    private String banquanName;

    @Column(name = "gongsi_name")
    @ApiModelProperty(value = "公司名称")
    private String gongsiName;

    @Column(name = "beizhu")
    @ApiModelProperty(value = "备注名")
    private String beizhu;

    @Column(name = "shangxian_num")
    @ApiModelProperty(value = "上线数量")
    private Integer shangxianNum;

    @Column(name = "weishangxian_num")
    @ApiModelProperty(value = "未线数量")
    private Integer weishagnxianNum;

    @Column(name = "jijiang_guoqi")
    @ApiModelProperty(value = "即将过期数量")
    private Integer jijiangGuoqi;

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
