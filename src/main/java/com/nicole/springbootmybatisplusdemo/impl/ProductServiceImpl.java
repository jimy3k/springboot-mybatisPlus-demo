package com.nicole.springbootmybatisplusdemo.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nicole.springbootmybatisplusdemo.mapper.ProductMapper;
import com.nicole.springbootmybatisplusdemo.pojo.Product;
import com.nicole.springbootmybatisplusdemo.service.ProductService;
import org.springframework.stereotype.Service;

@DS("slave")    //指定数据源
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
