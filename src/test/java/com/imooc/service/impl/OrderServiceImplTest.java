package com.imooc.service.impl;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {
    @Autowired
    OrderServiceImpl orderService;

    @Test
    public void create() throws Exception{
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("测试下单");
        orderDTO.setBuyerAddress("测试下单地址");
        orderDTO.setBuyerPhone("123123");
        orderDTO.setBuyerOpenid("110");
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("998");
        o1.setProductQuantity(5);
        orderDetailList.add(o1);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        System.out.println(result.getOrderId());
    }

    @Test
    public void findOne() {
        OrderDTO result = orderService.findOne("5488161586371676883");
        System.out.println(result.getOrderAmount());
        Assert.assertNotNull(result);
    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0,2 );
        Page<OrderDTO> orderDTOPage = orderService.findList("110", request);
        Assert.assertNotNull(orderDTOPage);
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne("5488161586371676883");
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(result.getOrderStatus(), OrderStatusEnum.CANCEL.getStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne("5488161586371676883");
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(result.getOrderStatus(), OrderStatusEnum.FINISHED.getStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne("5488161586371676883");
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getStatus(), result.getPayStatus());
    }

    @Test
    public void list() {
        PageRequest request = new PageRequest(0,2 );
        Page<OrderDTO> orderDTOPage = orderService.findList("110", request);
        Assert.assertNotNull(orderDTOPage);
    }
}