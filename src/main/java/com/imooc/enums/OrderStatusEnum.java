package com.imooc.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum {
    NEW(0, "新下单"),
    FINISHED(1, "完结"),
    CANCEL(2, "取消"),

    ;
    private Integer status;
    private String msg;

    OrderStatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
