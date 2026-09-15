package org.example.mall.service;

import org.example.mall.entity.Product;
import org.example.mall.entity.request.AddProductReq;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProductService {
    void add(AddProductReq addProductReq);

    void update(Product updateProduct);

    void delete(Integer id);

    void batchUpdateProductStatus(@RequestParam Integer[] ids, @RequestParam Integer sellStatus);
}
