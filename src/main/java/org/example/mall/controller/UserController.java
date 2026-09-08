package org.example.mall.controller;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;
import org.example.mall.common.ApiRestResponse;
import org.example.mall.common.Constant;
import org.example.mall.entity.User;
import org.example.mall.exception.MallException;
import org.example.mall.exception.MallExceptionEnum;
import org.example.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/test")
    @ResponseBody
    public User personalPage() {
        return userService.getUser();
    }

    @PostMapping("/register")
    @ResponseBody
    public ApiRestResponse register(@RequestParam String username, @RequestParam String password) throws MallException {
        if (StringUtils.isEmpty(username)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }
        if (password.length() < 8) {
            return ApiRestResponse.error(MallExceptionEnum.SHORT_PASSWORD);
        }
        userService.register(username, password);

        return ApiRestResponse.success();
    }

    @PostMapping("/login")
    @ResponseBody
    public ApiRestResponse login(@RequestParam String username, @RequestParam String password, HttpSession session) throws MallException {
        if (StringUtils.isEmpty(username)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(username, password);
        user.setPassword(null);
        session.setAttribute(Constant.MALL_USER, user);
        return ApiRestResponse.success(user);
    }

    @PostMapping("/user/update")
    @ResponseBody
    public ApiRestResponse updateUserInfo(HttpSession session, @RequestParam String signature) throws MallException {
        User user = (User) session.getAttribute(Constant.MALL_USER);
        if (user == null) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }
        User user1 = new User();
        user1.setId(user.getId());
        user1.setPersonalizedSignature(signature);
        userService.updateInfo(user1);
        return ApiRestResponse.success();
    }

    @PostMapping("/user/logout")
    @ResponseBody
    public ApiRestResponse logout(HttpSession session) {
        session.removeAttribute(Constant.MALL_USER);
        return ApiRestResponse.success();
    }

    @PostMapping("/admin/login")
    @ResponseBody
    public ApiRestResponse adminLogin(@RequestParam String username, @RequestParam String password, HttpSession session) throws MallException {
        if (StringUtils.isEmpty(username)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }
        if (StringUtils.isEmpty(password)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }
        User user = userService.login(username, password);
        if (userService.checkAdminRole(user)) {
            user.setPassword(null);
            session.setAttribute(Constant.MALL_USER, user);
            return ApiRestResponse.success(user);
        } else {
            return ApiRestResponse.error(MallExceptionEnum.NOT_ADMIN);
        }

    }
}
