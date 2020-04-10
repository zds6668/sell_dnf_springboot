package com.imooc.utils;

import com.imooc.dataobject.OrderMaster;
import com.imooc.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMaster2OrderDTO {
    public static OrderDTO convert(OrderMaster orderMaster) {
        OrderDTO result = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, result);
        return result;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
        return orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
