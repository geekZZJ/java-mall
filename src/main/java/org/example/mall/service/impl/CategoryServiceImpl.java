package org.example.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.mall.entity.Category;
import org.example.mall.entity.request.AddCategoryReq;
import org.example.mall.entity.vo.CategoryVo;
import org.example.mall.exception.MallException;
import org.example.mall.exception.MallExceptionEnum;
import org.example.mall.mapper.CategoryMapper;
import org.example.mall.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void update(Category category) {
        if (category.getName() != null) {
            Category category1 = categoryMapper.selectByName(category.getName());
            if (category1 != null && !category1.getId().equals(category.getId())) {
                throw new MallException(MallExceptionEnum.SAME_USER_NAME);
            }
        }
        int count = categoryMapper.updateByPrimaryKeySelective(category);
        if (count == 0) {
            throw new MallException(MallExceptionEnum.CREATE_USER_FAIL);
        }
    }

    @Override
    public void delete(Integer id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        if (category == null) {
            throw new MallException(MallExceptionEnum.DELETE_FAIL);
        }
        int count = categoryMapper.deleteByPrimaryKey(id);
        if (count == 0) {
            throw new MallException(MallExceptionEnum.DELETE_FAIL);
        }
    }

    @Override
    public PageInfo<CategoryVo> listForAdmin(Integer page, Integer limit) {
        PageHelper.startPage(page, limit, "type,order_num");
        List<Category> categories = categoryMapper.selectList();
        return new PageInfo(categories);
    }

    @Override
    public List<CategoryVo> listCategoryForCustomer() {
        ArrayList<CategoryVo> categoryVos = new ArrayList<CategoryVo>();
        recursiveFindCategories(categoryVos, 0);
        return categoryVos;
    }

    private void recursiveFindCategories(List<CategoryVo> categoryVos, Integer parentId) {
        List<Category> categoryList = categoryMapper.selectCategoryByParentId(parentId);
        if (!CollectionUtils.isEmpty(categoryList)) {
            for (int i = 0; i < categoryList.size(); i++) {
                Category category = categoryList.get(i);
                CategoryVo categoryVo = new CategoryVo();
                BeanUtils.copyProperties(category, categoryVo);
                categoryVos.add(categoryVo);
                recursiveFindCategories(categoryVo.getChildren(), categoryVo.getId());
            }
        }
    }
}
