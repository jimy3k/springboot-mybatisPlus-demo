package com.nicole.springbootmybatisplusdemo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;


@Data
@TableName("product")   //设置实体类对应的表名
public class Product {

    @TableId    //将id字段指定为主键
    private Integer id;

    @TableField("name")   //指定属性所对应的字段名
    private String name;
    private Integer price;

    @Version              //标识乐观锁版本号字段
    private Integer version;

}
