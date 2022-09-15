package com.nicole.springbootmybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nicole.springbootmybatisplusdemo.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository   //将类或者接口 标识为持久层组件
public interface UserMapper extends BaseMapper<User> {

    /*
     *  根据id查询用户信息，返回结果为Map集合。 自定义方法
     */
    Map<String, Object> selectMapById(long id);
}
