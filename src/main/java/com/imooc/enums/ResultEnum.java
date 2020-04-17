package com.imooc.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0, "成功"),
    PRODUCTNOTEXIST(10, "商品不存在"),
    PRODUCTSTOCKERROR(11, "库存不足"),
    ORDERNOTEXIST(12, "订单不存在"),
    ORDERDETAILNOTEXIST(13, "订单详情不存在"),
    ORDERSTATUSERROR(14, "订单状态异常"),
    ORDERUPDATEFAIL(15, "订单更新失败"),
    ORDERDETAILEMPTY(16, "订单详情为空"),
    PAYSTATUSERROR(17, "支付状态不正确"),
    PARAMERROR(18, "参数不正确"),
    CARTEMPTY(19, "购物车空的"),
    ORDERFINISH(20, "订单已完结"),
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer i, String msg) {
        this.code = i;
        this.msg = msg;
    }
}
