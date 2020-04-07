package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    OrderDetailRepository repository;

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = repository.findByOrderId("1");
        Assert.assertEquals(1, orderDetailList.size());
    }

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("1");
        orderDetail.setDetailId("999");
        orderDetail.setProductIcon("xx.jpg");
        orderDetail.setProductId("999");
        orderDetail.setProductName("买武器");
        orderDetail.setProductPrice(new BigDecimal(2.3));
        orderDetail.setProductQuantity(10);
        Assert.assertNotNull(repository.save(orderDetail));
    }
}