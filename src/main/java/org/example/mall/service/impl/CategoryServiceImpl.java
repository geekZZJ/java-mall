package org.example.mall.service.impl;

import org.example.mall.entity.Category;
import org.example.mall.entity.request.AddCategoryReq;
import org.example.mall.mapper.CategoryMapper;
import org.example.mall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    public void add(AddCategoryReq addCategoryReq) {
        Category category = new Category();
        BeanUtils.copyProperties(addCategoryReq, category);
    }
}
