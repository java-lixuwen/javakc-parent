package com.javakc.conpyriht.banquanyinjin.controller;

import com.javakc.commonuitls.api.APICODE;
import com.javakc.conpyriht.banquanyinjin.entity.Banquanyinjin;
import com.javakc.conpyriht.banquanyinjin.service.BanquanyinjinService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Progrom: javakc-parent
 * @ClassName: BanquanyinjinController
 * @Description: TODO
 * @Outhor: lixuwen
 * @Create: 2022/1/14 22:03
 */
@RestController
@RequestMapping("/conpyriht/banquanyinjin")
public class BanquanyinjinController {

    @Autowired
    private BanquanyinjinService banquanyinjinService;

    @ApiOperation("查询所有版权的数据")
    @GetMapping
    private APICODE findAll() {

        List<Banquanyinjin> list = banquanyinjinService.findAll();
        return APICODE.OK().data("items",list);


    }
}
