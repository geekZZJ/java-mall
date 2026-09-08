package org.example.mall.service;

import org.example.mall.entity.User;
import org.example.mall.exception.MallException;

public interface UserService {
    User getUser();

    void register(String username, String password) throws MallException;

    User login(String username, String password) throws MallException;

    void updateInfo(User user) throws MallException;

    boolean checkAdminRole(User user);
}