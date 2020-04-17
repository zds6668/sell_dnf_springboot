package com.imooc.enums;

import lombok.Getter;

/**
 * 商品状态枚举
 */
@Getter
public enum ProductStatusEnum implements CodeEnum{
    UP(0, "出售中"),
    DOWN(1, "已下架")
    ;
    private Integer status;
    private String descrip;

    ProductStatusEnum(Integer status, String descrip) {
        this.descrip = descrip;
        this.status = status;
    }
}
