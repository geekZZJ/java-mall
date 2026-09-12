package org.example.mall.service;

import org.example.mall.entity.Category;
import org.example.mall.entity.request.AddCategoryReq;

public interface CategoryService {

    void add(AddCategoryReq addCategoryReq);

    void update(Category category);
}
