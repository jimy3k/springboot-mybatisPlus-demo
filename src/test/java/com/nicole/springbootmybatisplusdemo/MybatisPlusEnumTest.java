package com.nicole.springbootmybatisplusdemo;

import com.nicole.springbootmybatisplusdemo.enums.SexEnum;
import com.nicole.springbootmybatisplusdemo.mapper.UserMapper;
import com.nicole.springbootmybatisplusdemo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisPlusEnumTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void tstInsert() {

        User user = new User();
        user.setName("小明");
        user.setAge(22);
        user.setEmail("xiaoming@baomidou.com");
        user.setSex(SexEnum.MALE);
        int result = userMapper.insert(user);
        System.out.println(result);

    }

    @Test
    public void tstSelectById() {

        User user = userMapper.selectById(46);
        System.out.println(user);
        System.out.println(user.getSex().getSexName());
        System.out.println(user.getSex().getSex());
        System.out.println(user.getSex().name());
    }
}
