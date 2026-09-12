package org.example.mall.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.mall.common.ApiRestResponse;
import org.example.mall.common.Constant;
import org.example.mall.entity.Category;
import org.example.mall.entity.User;
import org.example.mall.entity.request.AddCategoryReq;
import org.example.mall.entity.request.UpdateCategoryReq;
import org.example.mall.entity.vo.CategoryVo;
import org.example.mall.exception.MallExceptionEnum;
import org.example.mall.service.CategoryService;
import org.example.mall.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class CategoryController {
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;

    @Operation(summary = "后台添加目录")
    @PostMapping("/admin/category/add")
    @ResponseBody
    public ApiRestResponse addCategory(HttpSession session, @Valid @RequestBody AddCategoryReq addCategoryReq) {
        User user = (User) session.getAttribute(Constant.MALL_USER);
        if (user == null) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        Boolean adminRole = userService.checkAdminRole(user);
        if (adminRole) {
            categoryService.add(addCategoryReq);
            return ApiRestResponse.success();
        } else {
            return ApiRestResponse.error(MallExceptionEnum.NOT_ADMIN);
        }
    }

    @Operation(summary = "后台更新目录")
    @PostMapping("/admin/category/update")
    @ResponseBody
    public ApiRestResponse updateCategory(HttpSession session, @Valid @RequestBody UpdateCategoryReq updateCategoryReq) {
        User user = (User) session.getAttribute(Constant.MALL_USER);
        if (user == null) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        Boolean adminRole = userService.checkAdminRole(user);
        if (adminRole) {
            Category category = new Category();
            BeanUtils.copyProperties(updateCategoryReq, category);
            categoryService.update(category);
            return ApiRestResponse.success();
        } else {
            return ApiRestResponse.error(MallExceptionEnum.NOT_ADMIN);
        }
    }

    @Operation(summary = "后台删除目录")
    @DeleteMapping("/admin/category/delete")
    @ResponseBody
    public ApiRestResponse deleteCategory(@RequestParam Integer id) {
        categoryService.delete(id);
        return ApiRestResponse.success();
    }

    @Operation(summary = "后台目录列表")
    @PostMapping("/admin/category/list")
    @ResponseBody
    public ApiRestResponse listCategoryForAdmin(@RequestParam Integer page, @RequestParam Integer size) {
        PageInfo pageInfo = categoryService.listForAdmin(page, size);
        return ApiRestResponse.success(pageInfo);
    }

    @Operation(summary = "前台目录列表")
    @PostMapping("/category/list")
    @ResponseBody
    public ApiRestResponse listCategoryForCustomer() {
        List<CategoryVo> categoryVos = categoryService.listCategoryForCustomer();
        return ApiRestResponse.success(categoryVos);
    }
}
