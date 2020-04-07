package com.imooc.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    WAIT(0, "待付钱"),
    SUCCESS(1, "支付成功"),
    ;
    private Integer status;
    private String msg;

    PayStatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
