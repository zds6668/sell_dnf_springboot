package com.imooc.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PRODUCTNOTEXIST(10, "商品不存在"),
    PRODUCTSTOCKERROR(11, "库存不足")
    ;
    private Integer code;
    private String msg;

    ResultEnum(Integer i, String msg) {
        this.code = i;
        this.msg = msg;
    }
}
