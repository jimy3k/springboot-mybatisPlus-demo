package com.nicole.springbootmybatisplusdemo;

import com.nicole.springbootmybatisplusdemo.mapper.ProductMapper;
import com.nicole.springbootmybatisplusdemo.pojo.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisPlusVersionTest {

    @Autowired
    private ProductMapper productMapper;

    /*
     * 测试商品价格更新，不使用乐观锁插件，容易出现的冲突问题
     */
    @Test
    public void tstProductPrice() {

        //小李查询商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李查询到的商品价格：" + productLi.getPrice());

        //小王查询商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小王查询到的商品价格：" + productWang.getPrice());

        //小李将商品价格+50
        productLi.setPrice(productLi.getPrice() + 50);
        productMapper.updateById(productLi);

        //小王将商品价格-30
        productWang.setPrice(productWang.getPrice() - 30);
        productMapper.updateById(productWang);

        //老板查询商品价格
        Product productBoss = productMapper.selectById(1);
        System.out.println("老板最后查询到的商品价格：" + productBoss.getPrice());

    }

    /*
     * 测试商品价格更新，使用乐观锁插件，解决容易出现的冲突问题
     */
    @Test
    public void tstProductVersion() {

        //小李查询商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李查询到的商品价格：" + productLi.getPrice());

        //小王查询商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小王查询到的商品价格：" + productWang.getPrice());

        //小李将商品价格+50
        productLi.setPrice(productLi.getPrice() + 50);
        int result = productMapper.updateById(productLi);

        //小王将商品价格-30
        productWang.setPrice(productWang.getPrice() - 30);
        result = productMapper.updateById(productWang);
        //更新失败，重试。利用乐观锁的机制，解决冲突问题。
        while (result == 0) {
            productWang = productMapper.selectById(1);
            productWang.setPrice(productWang.getPrice() - 30);
            result = productMapper.updateById(productWang);
        }

        //老板查询商品价格
        Product productBoss = productMapper.selectById(1);
        System.out.println("老板最后查询到的商品价格：" + productBoss.getPrice());

    }
}
