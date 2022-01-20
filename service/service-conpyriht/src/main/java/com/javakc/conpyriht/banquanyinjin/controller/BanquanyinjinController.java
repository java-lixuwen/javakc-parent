package com.javakc.conpyriht.banquanyinjin.controller;

import com.javakc.commonuitls.api.APICODE;
import com.javakc.conpyriht.banquanyinjin.entity.Banquanyinjin;
import com.javakc.conpyriht.banquanyinjin.service.BanquanyinjinService;
import com.javakc.conpyriht.banquanyinjin.vo.BanquanyinjinQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "版权引进")
@CrossOrigin
public class BanquanyinjinController {

    @Autowired
    private BanquanyinjinService banquanyinjinService;

    @ApiOperation("查询所有版权的数据")
    @GetMapping
    private APICODE findAll() {

        List<Banquanyinjin> list = banquanyinjinService.findAll();
        return APICODE.OK().data("items", list);
    }

    @ApiOperation("版权分页查询")
    @PostMapping("{pageNo}/{pageSize}")
    public APICODE pageBanquanyinjin(@RequestBody(required = false) BanquanyinjinQuery banquanyinjinQuery, @PathVariable Integer pageNo, @PathVariable Integer pageSize) {

        Page<Banquanyinjin> page = banquanyinjinService.pageBanquanyinjin(banquanyinjinQuery, pageNo, pageSize);

        long totalElements = page.getTotalElements();  //得到数据总数
        List<Banquanyinjin> list = page.getContent();  //得到数据

        return APICODE.OK().data("total", totalElements).data("items", list);  //返回给前端  数据总数   数据
    }

    /**
     * 新增版权
     * @param banquanyinjin
     * @return
     */
    @ApiOperation(value = "新增版权-版权引进")
    @PostMapping("saveBanquanyinjin")
    public APICODE saveBanquanyinjin(@RequestBody Banquanyinjin banquanyinjin){
        banquanyinjinService.saveOrUpdate(banquanyinjin);
        return APICODE.OK();
    }

    /**
     * 根据id删除版权
     * @param banduanId
     * @return
     */
    @ApiOperation(value = "根据id删除版权-版权引进")
    @DeleteMapping("{banduanId}")
    public APICODE daleteBanquanyinjin(Integer banduanId){
        banquanyinjinService.removeById(banduanId);
        return APICODE.OK();
    }

    /**
     * 根据id获取单条数据
     * @param banquanId
     * @return
     */
    @ApiOperation(value = "根据id获取单条数据-版权引进")
    @GetMapping("{banduanId}")
    public APICODE view( @PathVariable Integer banquanId){

        Banquanyinjin banquanyinjin = banquanyinjinService.getById(banquanId);
        return APICODE.OK().data("banquanyinjing",banquanyinjin);
    }


    @ApiOperation(value = "修改版权-版权引进")
    @PutMapping("updateBanquanyinjin")
    public APICODE updateBanquanyinjin(@RequestBody Banquanyinjin banquanyinjin){
        banquanyinjinService.saveOrUpdate(banquanyinjin);
        return APICODE.OK();
    }



}













