package com.nicole.springbootmybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nicole.springbootmybatisplusdemo.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository   //将类或者接口 标识为持久层组件
public interface UserMapper extends BaseMapper<User> {

    /*
     *  根据id查询用户信息，返回结果为Map集合。 自定义方法
     */
    Map<String, Object> selectMapById(long id);

    /*
     * 根据age查询用户信息，并分页。 自定义方法
     * @Param page  MybatisPlus 所提供的分页对象，必须位于第一个参数位置
     * @Param age 查询条件参数
     */
    Page<User> selectPageVo(@Param("page") Page<User> page, @Param("age") Integer age);
}
