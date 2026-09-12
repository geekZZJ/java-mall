package org.example.mall.service;

import com.github.pagehelper.PageInfo;
import org.example.mall.entity.Category;
import org.example.mall.entity.request.AddCategoryReq;
import org.example.mall.entity.vo.CategoryVo;

import java.util.List;

public interface CategoryService {

    void add(AddCategoryReq addCategoryReq);

    void update(Category category);

    void delete(Integer id);

    PageInfo<CategoryVo> listForAdmin(Integer page, Integer limit);

    List<CategoryVo> listCategoryForCustomer();
}
