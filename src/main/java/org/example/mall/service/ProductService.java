package org.example.mall.service;

import org.example.mall.entity.Product;
import org.example.mall.entity.request.AddProductReq;

public interface ProductService {
    void add(AddProductReq addProductReq);

    void update(Product updateProduct);

    void delete(Integer id);
}
