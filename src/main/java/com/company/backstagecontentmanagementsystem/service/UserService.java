package com.company.backstagecontentmanagementsystem.service;


import com.company.backstagecontentmanagementsystem.dao.UserMapper;
import com.company.backstagecontentmanagementsystem.domain.User;
import com.company.backstagecontentmanagementsystem.util.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User login(String phone, String password) {
        User user = userMapper.login(phone, MD5Utils.encode(password));
        if (user != null) {
            try {
                userMapper.updateVisitTime(user.getUserId());
            } catch (SQLException e) {
                logger.error("login failed", e);
            }
        }
        return user;
    }

    public boolean register(String phone, String password) {
        try {
            userMapper.register(phone, MD5Utils.encode(password));
            return true;
        } catch (SQLException e) {
            logger.error("register failed", e);
            return false;
        }
    }

    public boolean isRegistered(String phone) {
        return userMapper.findCountByPhone(phone) > 0;
    }
}