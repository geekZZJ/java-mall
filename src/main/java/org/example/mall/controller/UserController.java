package org.example.mall.controller;

import io.micrometer.common.util.StringUtils;
import org.example.mall.common.ApiRestResponse;
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
}
