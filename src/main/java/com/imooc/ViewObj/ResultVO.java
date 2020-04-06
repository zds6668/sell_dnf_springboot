package com.imooc.ViewObj;

import lombok.Data;

/**
 * http请求返回的对象
 */
@Data
public class ResultVO<T> {
    private Integer code;

    private String msg;

    private T data;
}
