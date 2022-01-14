package com.javakc.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @Progrom: javakc-parent
 * @ClassName: OssApplication
 * @Description: TODO
 * @Outhor: lixuwen
 * @Create: 2022/1/14 11:28
 */
// exclude = DataSourceAutoConfiguration.class  这个属性 表示：当前服务启动时候没有提供数据源，也就是没有连接数据库
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.javakc"})
public class OssApplication {

    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class,args);
    }
}
