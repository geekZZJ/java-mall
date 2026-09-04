package org.example.mall.service.impl;

import org.example.mall.entity.User;
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
}
