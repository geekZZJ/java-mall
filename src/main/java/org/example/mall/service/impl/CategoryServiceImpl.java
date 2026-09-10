package org.example.mall.service.impl;

import org.example.mall.entity.Category;
import org.example.mall.entity.request.AddCategoryReq;
import org.example.mall.exception.MallException;
import org.example.mall.exception.MallExceptionEnum;
import org.example.mall.mapper.CategoryMapper;
import org.example.mall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public void add(AddCategoryReq addCategoryReq) {
        Category category = new Category();
        BeanUtils.copyProperties(addCategoryReq, category);
        Category categoryOld = categoryMapper.selectByName(addCategoryReq.getName());
        if (categoryOld != null) {
            throw new MallException(MallExceptionEnum.SAME_USER_NAME);
        }
        int count = categoryMapper.insertSelective(category);
        if (count == 0) {
            throw new MallException(MallExceptionEnum.CREATE_USER_FAIL);
        }
    }
}
