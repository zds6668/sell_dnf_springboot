package com.imooc.service;

import com.imooc.dataobject.SellerInfo;

public interface SellerService {
    SellerInfo findSellerInfoByUsername(String username);
}
