package org.example.mall.controller;

import jakarta.servlet.http.HttpSession;
import org.example.mall.common.ApiRestResponse;
import org.example.mall.common.Constant;
import org.example.mall.entity.User;
import org.example.mall.entity.request.AddCategoryReq;
import org.example.mall.exception.MallExceptionEnum;
import org.example.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CategoryController {
    @Autowired
    UserService userService;

    @PostMapping("/admin/category/add")
    @ResponseBody
    public ApiRestResponse addCategory(HttpSession session, AddCategoryReq addCategoryReq) {
        if (addCategoryReq.getName() == null || addCategoryReq.getType() == null || addCategoryReq.getParentId() == null || addCategoryReq.getOrderNum() == null) {
            return ApiRestResponse.error(MallExceptionEnum.PARAM_NOT_NULL);
        }
        User user = (User) session.getAttribute(Constant.MALL_USER);
        if (user == null) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        Boolean adminRole = userService.checkAdminRole(user);
        if (adminRole) {

        } else {
            return ApiRestResponse.error(MallExceptionEnum.NOT_ADMIN);
        }
    }
}
