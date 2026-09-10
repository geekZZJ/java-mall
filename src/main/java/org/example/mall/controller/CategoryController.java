package org.example.mall.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.mall.common.ApiRestResponse;
import org.example.mall.common.Constant;
import org.example.mall.entity.User;
import org.example.mall.entity.request.AddCategoryReq;
import org.example.mall.exception.MallExceptionEnum;
import org.example.mall.service.CategoryService;
import org.example.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CategoryController {
    @Autowired
    UserService userService;
    @Autowired
    CategoryService categoryService;

    @PostMapping("/admin/category/add")
    @ResponseBody
    public ApiRestResponse addCategory(HttpSession session,@Valid @RequestBody AddCategoryReq addCategoryReq) {
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
}
