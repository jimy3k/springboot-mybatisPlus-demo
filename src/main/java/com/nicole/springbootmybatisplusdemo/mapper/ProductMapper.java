package com.nicole.springbootmybatisplusdemo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nicole.springbootmybatisplusdemo.pojo.Product;
import org.springframework.stereotype.Repository;

@DS("slave")  //指定数据源
@Repository   //将类或者接口 标识为持久层组件
public interface ProductMapper extends BaseMapper<Product> {

}
