package com.imooc.service;

import com.imooc.dto.OrderDTO;

public interface BuyerService {
    OrderDTO findOneOrder(String openId, String orderId);
    OrderDTO cancelOneOrder(String openId, String orderId);
}
