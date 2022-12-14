package com.nicole.springbootmybatisplusdemo.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nicole.springbootmybatisplusdemo.mapper.UserMapper;
import com.nicole.springbootmybatisplusdemo.pojo.User;
import com.nicole.springbootmybatisplusdemo.service.UserService;
import org.springframework.stereotype.Service;

@DS("master")   //指定数据源
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
