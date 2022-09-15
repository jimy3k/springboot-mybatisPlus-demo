package com.nicole.springbootmybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nicole.springbootmybatisplusdemo.pojo.User;
import org.springframework.stereotype.Repository;

@Repository   //将类或者接口 标识为持久层组件
public interface UserMapper extends BaseMapper<User> {

}
