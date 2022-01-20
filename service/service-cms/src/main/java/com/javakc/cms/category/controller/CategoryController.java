package com.javakc.cms.category.controller;

import com.javakc.cms.category.service.CategoryService;
import com.javakc.cms.category.vo.YijiFenlei;
import com.javakc.commonuitls.api.APICODE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Progrom: javakc-parent
 * @ClassName: CategoryController
 * @Description: TODO
 * @Outhor: lixuwen
 * @Create: 2022/1/18 21:47
 */
@Api(tags = "分类管理")
@RestController
@RequestMapping("/cms/category")
@CrossOrigin   // 解决跨域
public class CategoryController {
    //注入service
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取树形结构数据-分类")
    @GetMapping
    public APICODE getCategoryList(){
        List<YijiFenlei> list = categoryService.getCategoryList();
        return APICODE.OK().data("items",list);
    }
}
