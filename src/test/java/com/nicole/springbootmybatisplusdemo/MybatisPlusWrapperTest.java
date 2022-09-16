package com.nicole.springbootmybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nicole.springbootmybatisplusdemo.mapper.UserMapper;
import com.nicole.springbootmybatisplusdemo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;

    /*
     * 条件构造器，查询记录测试
     */
    @Test
    public void tstSelectList() {

        //条件构造器，查询用户名包含’a‘,年龄在20到30之间，并且邮箱信息不为null的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a");
        queryWrapper.between("age", 20, 30);
        queryWrapper.isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);

    }

    /*
     * 条件构造器，查询记录测试，加上排序条件
     */
    @Test
    public void tstSelectList2() {

        //条件构造器，查询用户名包含’张三‘,年龄在20到30之间,邮箱信息不为null的用户信息；并且按照年龄的降序排序，若年龄相同，则按照id的升序排序。
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "张三");
        queryWrapper.between("age", 20, 30);
        queryWrapper.isNotNull("email");
        queryWrapper.orderByDesc("age");
        queryWrapper.orderByAsc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);

    }

    /*
     * 条件构造器，删除记录测试
     */
    @Test
    public void tstdelete() {

        //条件构造器，删除邮箱信息为null的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");

        int result = userMapper.delete(queryWrapper);
        System.out.println("删除记录行数：" + result + "\n");

        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);

    }

    /*
     * 条件构造器，更新记录测试
     */
    @Test
    public void tstUpdate() {

        //条件构造器，将（年龄大于20,用户名包含a）,或邮箱信息为null的用户信息修改；
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 20);
        queryWrapper.like("name", "a");
        queryWrapper.or();
        queryWrapper.isNull("email");

        User user = new User();
        user.setName("小明");
        user.setEmail("xiaoming@baomidou.com");

        int result = userMapper.update(user, queryWrapper);
        System.out.println("更新记录行数：" + result + "\n");

        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    /*
     * 条件构造器，更新记录测试，条件优先级发生变化
     */
    @Test
    public void tstUpdate2() {

        //条件构造器，将用户名包含’小‘并且（年龄大于20或邮箱信息为null）的用户信息修改；
        //lambda 中的条件优先执行
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "小");
        queryWrapper.and(i->i.gt("age",20).or().isNull("email"));

        User user = new User();
        user.setName("小红");
        user.setEmail("xiaohong@baomidou.com");

        int result = userMapper.update(user, queryWrapper);
        System.out.println("更新记录行数：" + result + "\n");

        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }
}
