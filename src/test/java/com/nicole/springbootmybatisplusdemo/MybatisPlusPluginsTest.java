package com.nicole.springbootmybatisplusdemo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nicole.springbootmybatisplusdemo.mapper.UserMapper;
import com.nicole.springbootmybatisplusdemo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisPlusPluginsTest {

    @Autowired
    private UserMapper userMapper;

    /*
     * 测试分页插件
     */
    @Test
    public void tstPageNation() {

        Integer curRentPage = 3;
        Integer pageSize = 3;

        Page<User> page = new Page<>(1, 3);

        page.setCurrent(curRentPage);
        page.setSize(pageSize);

        userMapper.selectPage(page, null);

        System.out.println("===========当前页：" + curRentPage + "===========");
        List<User> userList = page.getRecords();   //当前页的所有记录集合列表
        userList.forEach(System.out::println);

        curRentPage++;
        page.setCurrent(curRentPage);

        userMapper.selectPage(page, null);

        System.out.println("===========当前页：" + curRentPage + "===========");
        userList = page.getRecords();   //当前页的所有记录集合列表
        userList.forEach(System.out::println);

        System.out.println("当前页/总页数：" + page.getCurrent() + "/" + page.getPages());
        System.out.println("总记录数：" + page.getTotal());

        System.out.println("有上一页：" + (page.hasPrevious() ? "有" : "无"));
        System.out.println("有下一页：" + (page.hasNext() ? "有" : "无"));

    }

}
