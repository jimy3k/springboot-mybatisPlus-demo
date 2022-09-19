package com.nicole.springbootmybatisplusdemo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum SexEnum {

    MALE(1, "男"),
    FEMALE(0, "女");

    @EnumValue     //将注解所标注的属性值，存储到数据库中
    private Integer sex;
    private String sexName;

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
