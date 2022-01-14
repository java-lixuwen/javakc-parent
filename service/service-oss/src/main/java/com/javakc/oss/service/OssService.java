package com.javakc.oss.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.javakc.oss.utils.PropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Progrom: javakc-parent
 * @ClassName: OssService
 * @Description: TODO
 * @Outhor: lixuwen
 * @Create: 2022/1/14 13:50
 */
@Service
public class OssService {

    public String uploadFile(MultipartFile file){
        // 读取配置文件
        String endPoint = PropertiesUtils.END_POINT;
        String keyId = PropertiesUtils.KEY_ID;
        String keySecret = PropertiesUtils.KEY_SECRET;
        String bucketName = PropertiesUtils.BUCKET_NAME;

        /**
         * 将oss中的上传文件流 粘贴过来  需要改里面的代码
         */

           // ⑦
            String uploadUrl = null;
        try {
            // ①yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
            String endpoint = endPoint;
            // ①阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
            String accessKeyId = keyId;
            // ①
            String accessKeySecret = keySecret;
            //② 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //③ 获取文件流
            InputStream inputStream = file.getInputStream();
            //④设置日期路径    就是 将时间设置成文件夹的名字
            String filePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            //⑤设置文件名字
            String originalFilename = file.getOriginalFilename(); // 文件的原始名字
            String fileName = UUID.randomUUID().toString().replaceAll("-","");  // 将文件名字设置成 UUID
            String fileType = originalFilename.substring(originalFilename.indexOf("."));  // 截取文件后面的后缀
            String newName = fileName + fileType;    // 新名字 = 文件名 + 文件类型
            String url = "image/"+filePath+"/"+newName;   // 拼接上传的路径

            // ⑥依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, url, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            // https://javakc-wenjian.oss-cn-beijing.aliyuncs.com/yuwenshu.jpg
            uploadUrl = "https://"+bucketName + "." + endpoint + "/" + url;  //⑧ 这换回的URL拼接，需要对照 oss文件管理里面的路径格式，进行拼接。
        }catch (Exception e){
            e.printStackTrace();
        }
        // ⑨
        return uploadUrl;
    }


    public String updateuploadFile(MultipartFile file){

        // 读取配置文件
        String endPoint = PropertiesUtils.END_POINT;
        String keyId = PropertiesUtils.KEY_ID;
        String keySecret = PropertiesUtils.KEY_SECRET;
        String bucketName = PropertiesUtils.BUCKET_NAME;

        /**
         * 将oss中的上传文件流 粘贴过来  需要改里面的代码
         */

        // ⑦
        String updateuploadUrl = null;
        try {
            // ①yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
            String endpoint = endPoint;
            // ①阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
            String accessKeyId = keyId;
            // ①
            String accessKeySecret = keySecret;
            //② 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //③ 获取文件流
            InputStream inputStream = file.getInputStream();
            //④设置日期路径    就是 将时间设置成文件夹的名字
            String filePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            //⑤设置文件名字
            String originalFilename = file.getOriginalFilename(); // 文件的原始名字
            String fileName = UUID.randomUUID().toString().replaceAll("-","");  // 将文件名字设置成 UUID
            String fileType = originalFilename.substring(originalFilename.indexOf("."));  // 截取文件后面的后缀
            String newName = fileName + fileType;    // 新名字 = 文件名 + 文件类型
            String url = "image/"+filePath+"/"+newName;   // 拼接上传的路径

            // ⑥依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, url, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            // https://javakc-wenjian.oss-cn-beijing.aliyuncs.com/yuwenshu.jpg
            updateuploadUrl = "https://"+bucketName + "." + endpoint + "/" + url;  //⑧ 这换回的URL拼接，需要对照 oss文件管理里面的路径格式，进行拼接。
        }catch (Exception e){
            e.printStackTrace();
        }
        // ⑨
        return updateuploadUrl;
    }


}
