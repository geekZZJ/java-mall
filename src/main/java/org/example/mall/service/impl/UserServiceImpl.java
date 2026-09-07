package org.example.mall.service.impl;

import org.example.mall.entity.User;
import org.example.mall.exception.MallException;
import org.example.mall.exception.MallExceptionEnum;
import org.example.mall.mapper.UserMapper;
import org.example.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser() {
        return userMapper.selectByPrimaryKey(10);
    }

    @Override
    public void register(String username, String password) throws MallException {
        User result = userMapper.selectByName(username);
        if (result != null) {
            throw new MallException(MallExceptionEnum.SAME_USER_NAME);
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Integer count = userMapper.insertSelective(user);
        if (count == 0) {
            throw new MallException(MallExceptionEnum.CREATE_USER_FAIL);
        }
    }
}
