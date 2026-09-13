package org.example.mall.service.impl;

import org.example.mall.entity.Product;
import org.example.mall.entity.request.AddProductReq;
import org.example.mall.exception.MallException;
import org.example.mall.exception.MallExceptionEnum;
import org.example.mall.mapper.ProductMapper;
import org.example.mall.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public void add(AddProductReq addProductReq) {
        Product product = new Product();
        BeanUtils.copyProperties(addProductReq, product);
        Product product1 = productMapper.selectByName(addProductReq.getName());
        if (product1 != null) {
            throw new MallException(MallExceptionEnum.SAME_USER_NAME);
        }
        int count = productMapper.insert(product);
        if (count == 0) {
            throw new MallException(MallExceptionEnum.CREATE_USER_FAIL);
        }
    }
}
