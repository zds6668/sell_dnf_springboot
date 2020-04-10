package com.imooc.service.impl;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;
    @Override
    public OrderDTO findOneOrder(String openId, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (null == orderDTO || !orderDTO.getBuyerOpenid().equalsIgnoreCase(openId)) {
            log.error("[查询订单] 订单openId与买家不一致, openId={}, orderId={}", openId, orderId);
            throw new SellException(ResultEnum.ORDERSTATUSERROR);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO cancelOneOrder(String openId, String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (null == orderDTO || !orderDTO.getBuyerOpenid().equalsIgnoreCase(openId)) {
            log.error("[取消订单] 订单openId与买家不一致, openId={}, orderId={}", openId, orderId);
            throw new SellException(ResultEnum.ORDERSTATUSERROR);
        }
        return orderService.cancel(orderDTO);
    }
}
