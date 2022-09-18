package com.nicole.springbootmybatisplusdemo;

import com.nicole.springbootmybatisplusdemo.pojo.User;
import com.nicole.springbootmybatisplusdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class MybatisPlusServiceTest {

    @Autowired
    private UserService userService;

    /*
     * 使用UserService，查询总记录数
     */
    @Test
    public void tstGetCount() {

        long count = userService.count();
        System.out.println("总记录数：" + count);

    }

    /*
     * 使用UserService，进行批量添加
     */
    @Test
    public void tstInsertMore() {

        List<User> userList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setName("张三" + i);
            user.setAge(22 + i);
            user.setEmail("tst" + i + "@baomidou.com");
            userList.add(user);
        }

        userService.saveBatch(userList);

        List<User> list = userService.list();
        list.forEach(System.out::println);

    }

}
