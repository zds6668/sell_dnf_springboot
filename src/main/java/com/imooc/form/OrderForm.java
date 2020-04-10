package com.imooc.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {
    @NotEmpty(message = "姓名必须有")
    private String name;

    @NotEmpty(message = "手机号要有")
    private String phone;

    @NotEmpty(message = "地址要有")
    private String address;

    @NotEmpty(message = "openId必须")
    private String openId;

    @NotEmpty(message = "购物车不能空")
    private String items;
}
