package com.imooc.controller;

import com.imooc.ViewObj.ResultVO;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.OrderForm;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import com.imooc.utils.OrderForm2OrderDTO;
import com.imooc.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    BuyerService buyerService;

    // 创订单
    @RequestMapping("/create")
    public ResultVO<Map<String, String>> creat(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("[创建订单]参数不正确, orderForm = {}", orderForm);
            throw new SellException(ResultEnum.PARAMERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("[创建订单]购物车不能空");
            throw new SellException(ResultEnum.CARTEMPTY);
        }
        OrderDTO creatResult = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<String, String>() {
            {put("orderId", creatResult.getOrderId());}
        };
        return ResultVOUtil.successs(map);
    }

    // 订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openId") String openId,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openId)) {
            log.error("[查询订单列表]openId为空");
            throw new SellException(ResultEnum.PARAMERROR);
        }
        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openId, request);
        return ResultVOUtil.successs(orderDTOPage.getContent());
    }

    // 订单详情
    @RequestMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openId") String openId,
                                     @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOneOrder(openId, orderId);
        return ResultVOUtil.successs(orderDTO);
    }

    // 取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openId") String openId,
                                     @RequestParam("orderId") String orderId) {
        buyerService.cancelOneOrder(openId, orderId);
        return ResultVOUtil.successs();
    }
}
