package com.nicole.springbootmybatisplusdemo;

import com.nicole.springbootmybatisplusdemo.mapper.UserMapper;
import com.nicole.springbootmybatisplusdemo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringbootMybatisPlusDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }

    /*
    *  测试查询数据
    */
    @Test
    public void tstSelectedList(){
        //通过Mapper接口，查询数据
        //int id = 3;
        //User user = userMapper.selectById(id);
        //System.out.println(user);

        //List<Long> list = Arrays.asList(1L,2L,3L);
        //List<User> userList = userMapper.selectBatchIds(list);
        //userList.forEach(System.out::println);

        //Map<String,Object> map = new HashMap<>();
        //map.put("name","Bill");
        //map.put("age",23);
        //List<User> userList = userMapper.selectByMap(map);
        //userList.forEach(System.out::println);

        Map<String,Object> map = userMapper.selectMapById(3L);  //自定义方法查询数据
        System.out.println(map);

        //List<User> list = userMapper.selectList(null);
        //list.forEach(System.out::println);

    }

    /*
     *  测试插入数据
     */
    @Test
    public void tstInsert(){
        //通过Mapper接口，插入数据
        User user = new User();
        //user.setId(6);
        user.setName("张三");
        user.setAge(22);
        user.setEmail("tst6@baomidou.com");
        int result = userMapper.insert(user);
        System.out.println("插入记录行数：" + result);

        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }


    /*
     *  测试删除数据
     */
    @Test
    public void tstDelete(){
        //通过Mapper接口，删除数据

        //int id = 7;
        //int result = userMapper.deleteById(id);

        //Map<String,Object> map = new HashMap<>();
        //map.put("name","张三");
        //map.put("age",22);
        //int result = userMapper.deleteByMap(map);

        List<Long> list1 = Arrays.asList(9L,10L,11L);
        int result = userMapper.deleteBatchIds(list1);

        System.out.println("删除记录行数：" + result);

        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    /*
     *  测试更新数据
     */
    @Test
    public void tstUpdate(){
        //通过Mapper接口，更新数据

        User user = new User();
        user.setId(5L);
        user.setName("Bill");
        user.setAge(23);
        user.setEmail("bill@baomidou.com");
        int result = userMapper.updateById(user);

        //Map<String,Object> map = new HashMap<>();
        //map.put("name","张三");
        //map.put("age",22);
        //int result = userMapper.deleteByMap(map);

        System.out.println("更新记录行数：" + result);

        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

}
