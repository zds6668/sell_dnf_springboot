package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.ProductInfoRepository;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        Optional<ProductInfo> result = repository.findById(productId);
        return result.get();
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getStatus());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO :
                cartDTOList) {
            Optional<ProductInfo> productInfo = repository.findById(cartDTO.getProductId());
            if (!productInfo.isPresent()) {
                throw new SellException(ResultEnum.PRODUCTNOTEXIST);
            }
            Integer result = productInfo.get().getProductStock() + cartDTO.getProductQuantity();
            productInfo.get().setProductStock(result);
            repository.save(productInfo.get());
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            Optional<ProductInfo> productInfo = repository.findById(cartDTO.getProductId());
            if (!productInfo.isPresent()) {
                throw new SellException(ResultEnum.PRODUCTNOTEXIST);
            }
            Integer result = productInfo.get().getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCTSTOCKERROR);
            }
            productInfo.get().setProductStock(result);
            repository.save(productInfo.get());
        }

    }
}
