package com.javakc.conpyriht.banquanyinjin.entity;

import com.google.gson.internal.$Gson$Preconditions;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.persistence.*;

/**
 * @Progrom: javakc-parent
 * @ClassName: Banquanyinjin
 * @Description: TODO
 * @Outhor: lixuwen
 * @Create: 2022/1/14 22:02
 */
@Data
@Entity
@Table(name = "conpyriht_banquanyinjin")
public class Banquanyinjin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "版权ID")
    private Integer id;

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


}
