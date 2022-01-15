package com.javakc.conpyriht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @Progrom: javakc-parent
 * @ClassName: ConpyrihtApplication
 * @Description: TODO
 * @Outhor: lixuwen
 * @Create: 2022/1/14 23:13
 */
@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.javakc"})
public class ConpyrihtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConpyrihtApplication.class,args);
    }


}
