package com.javakc.oss.controller;

import com.javakc.commonuitls.api.APICODE;
import com.javakc.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 阿里云OSS存储-控制器
 * @Progrom: javakc-parent
 * @ClassName: OssController
 * @Description: TODO
 * @Outhor: lixuwen
 * @Create: 2022/1/14 14:58
 */
@Api(tags = "阿里云OSS存储-控制器")
@RestController
@RequestMapping("/oss")
@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;

    @ApiOperation(value = "文件上传",notes = "阿里云OSS存储")
    @PostMapping("uploadFile")
    public APICODE uploadFile(MultipartFile file){
        String uploadUrl= ossService.uploadFile(file);
        return APICODE.OK().message("上传成功").data("uploadUrl",uploadUrl); // 返回给前台的url
    }


    @ApiOperation(value = "修改文件上传",notes = "阿里云OSS存储")
    @PostMapping("updateuploadFile")
    public APICODE updateuploadFile(MultipartFile file){
        String updateuploadUrl= ossService.updateuploadFile(file);
        return APICODE.OK().message("上传成功").data("updateuploadUrl",updateuploadUrl); // 返回给前台的url
    }


}
