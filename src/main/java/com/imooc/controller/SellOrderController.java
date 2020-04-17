package com.imooc.controller;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellOrderController {
    @Autowired
    OrderService orderService;
    /**
     * @param page 第几页
     * @param size 一页几个
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);
        map.put("currentSize", size);
        return new ModelAndView("order/list", map);
    }

    @RequestMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        OrderDTO orderDTO;
        try {
            orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (SellException e) {
          log.error("[卖家取消订单] 获取订单异常", e);
            map.put("msg", ResultEnum.ORDERNOTEXIST.getMsg());
            map.put("url", "/seller/order/list");
            map.put("status", ResultEnum.ORDERNOTEXIST.getCode());
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMsg());
        map.put("status", ResultEnum.SUCCESS.getCode());
        map.put("url", "/seller/order/list");
        return new ModelAndView("common/error", map);
    }

    @RequestMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        OrderDTO orderDTO;
        try {
            orderDTO = orderService.findOne(orderId);
        } catch (SellException e) {
            log.error("[订单详情页] 获取订单异常", e);
            map.put("msg", ResultEnum.ORDERNOTEXIST.getMsg());
            map.put("url", "/seller/order/list");
            map.put("status", ResultEnum.ORDERNOTEXIST.getCode());
            return new ModelAndView("common/error", map);
        }
        map.put("orderDTO", orderDTO);
        return new ModelAndView("order/detail", map);
    }

    @RequestMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        OrderDTO orderDTO;
        try {
            orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        } catch (SellException e) {
            log.error("[完结订单] 获取订单异常", e);
            map.put("msg", ResultEnum.ORDERNOTEXIST.getMsg());
            map.put("url", "/seller/order/list");
            map.put("status", ResultEnum.ORDERNOTEXIST.getCode());
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.ORDERFINISH.getMsg());
        map.put("status", ResultEnum.ORDERFINISH.getCode());
        map.put("url", "/seller/order/list");
        return new ModelAndView("common/error", map);
    }
}
