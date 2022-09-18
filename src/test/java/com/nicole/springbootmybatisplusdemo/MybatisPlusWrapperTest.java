package com.nicole.springbootmybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.nicole.springbootmybatisplusdemo.mapper.UserMapper;
import com.nicole.springbootmybatisplusdemo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

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
     * 条件构造器，测试查询部分字段 selectMaps
     */
    @Test
    public void tstSelectListApart() {

        //条件构造器，查询用户名、年龄、邮箱字段
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //选定查询字段
        queryWrapper.select("name", "age", "email");

        List<Map<String, Object>> mapList = userMapper.selectMaps(queryWrapper);
        mapList.forEach(System.out::println);

    }

    /*
     * 条件构造器，组合子查询
     */
    @Test
    public void tstSelectListSon() {

        //条件构造器，查询id<=100的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.inSql("id", "select id from user where id <= 100");

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);

    }

    /*
     * 条件构造器，拼装查询条件组合
     */
    @Test
    public void tstSelectListBuilder01() {

        //拼装查询条件组合
        String name = "小";
        Integer ageBegin = 20;
        Integer ageEnd = 40;
        String email = "xiao";

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            //isNotBlank 判断某个字符变量是否不为空字符串、不为null、不为空白符
            queryWrapper.like("name", name);
        }

        if (ageBegin != null) {
            queryWrapper.ge("age", ageBegin);
        }

        if (ageEnd != null) {
            queryWrapper.le("age", ageEnd);
        }

        if (StringUtils.isNotBlank(email)) {
            //isNotBlank 判断某个字符变量是否不为空字符串、不为null、不为空白符
            queryWrapper.like("email", email);
        }


        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);

    }

    /*
     * 条件构造器，拼装查询条件组合
     */
    @Test
    public void tstSelectListBuilder02() {

        //拼装查询条件组合
        String name = "小";
        Integer ageBegin = 20;
        Integer ageEnd = 40;
        String email = "xiao";

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        //isNotBlank 判断某个字符变量是否不为空字符串、不为null、不为空白符
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name);

        queryWrapper.ge(ageBegin != null, "age", ageBegin);

        queryWrapper.le(ageEnd != null, "age", ageEnd);

        //isNotBlank 判断某个字符变量是否不为空字符串、不为null、不为空白符
        queryWrapper.like(StringUtils.isNotBlank(email), "email", email);


        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);

    }

    /*
     * 条件构造器，拼装查询条件组合 LambdaQueryWrapper 的使用
     */
    @Test
    public void tstSelectListBuilder03() {

        //拼装查询条件组合
        String name = "小";
        Integer ageBegin = 20;
        Integer ageEnd = 40;
        String email = "xiao";

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        //isNotBlank 判断某个字符变量是否不为空字符串、不为null、不为空白符
        queryWrapper.like(StringUtils.isNotBlank(name), User::getName, name);

        queryWrapper.ge(ageBegin != null, User::getAge, ageBegin);

        queryWrapper.le(ageEnd != null, User::getAge, ageEnd);

        //isNotBlank 判断某个字符变量是否不为空字符串、不为null、不为空白符
        queryWrapper.like(StringUtils.isNotBlank(email), User::getEmail, email);


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
        queryWrapper.and(i -> i.gt("age", 20).or().isNull("email"));

        User user = new User();
        user.setName("小红");
        user.setEmail("xiaohong@baomidou.com");

        int result = userMapper.update(user, queryWrapper);
        System.out.println("更新记录行数：" + result + "\n");

        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    /*
     * 条件构造器，更新记录测试，使用 UpdateWrapper
     */
    @Test
    public void tstUpdate3() {

        //条件构造器，将用户名包含’小‘并且（年龄大于20或邮箱信息为null）的用户信息修改；
        //lambda 中的条件优先执行
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();

        updateWrapper.like("name", "小");
        updateWrapper.and(i -> i.gt("age", 20).or().isNull("email"));
        updateWrapper.set("name", "小黑");
        updateWrapper.set("email", "xiaohei@baomidou.com");

        int result = userMapper.update(null, updateWrapper);
        System.out.println("更新记录行数：" + result + "\n");

        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    /*
     * 条件构造器，拼装查询条件组合 LambdaUpdateWrapper 的使用
     */
    @Test
    public void tstUpdateBuilder01() {

        //拼装查询条件组合
        String name = "小";
        Integer ageBegin = 20;
        Integer ageEnd = 40;
        String email = "xiao";

        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();

        //isNotBlank 判断某个字符变量是否不为空字符串、不为null、不为空白符
        lambdaUpdateWrapper.like(StringUtils.isNotBlank(name), User::getName, name);

        lambdaUpdateWrapper.ge(ageBegin != null, User::getAge, ageBegin);

        lambdaUpdateWrapper.le(ageEnd != null, User::getAge, ageEnd);

        //isNotBlank 判断某个字符变量是否不为空字符串、不为null、不为空白符
        lambdaUpdateWrapper.like(StringUtils.isNotBlank(email), User::getEmail, email);

        lambdaUpdateWrapper.set(User::getName, "小红");
        lambdaUpdateWrapper.set(User::getEmail, "xiaohong@baomidou.com");

        int result = userMapper.update(null, lambdaUpdateWrapper);
        System.out.println("更新记录行数：" + result + "\n");

        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);

    }

}
