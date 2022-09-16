package com.nicole.springbootmybatisplusdemo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@TableName("user")   //设置实体类对应的表名
public class User {

    @TableId    //将id字段指定为主键
    private long id;

    @TableField("name")   //指定属性所对应的字段名
    private String name;
    private Integer age;
    private String email;

    @TableLogic             //指定逻辑删除字段
    private Integer isdeleted;

}
