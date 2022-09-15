package com.nicole.springbootmybatisplusdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nicole.springbootmybatisplusdemo.mapper")  //用于扫描Mapper接口所在的包
public class SpringbootMybatisPlusDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisPlusDemoApplication.class, args);
    }

}
