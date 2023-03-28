package com.cyh.service.impl;

import com.cyh.dao.UserDao;
import com.cyh.entity.User;
import com.cyh.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public User login(User user) {
        User user1 = userDao.login(user);
        System.out.println("user1 = " + user1);
        if (user1 != null) {
            return user1;
        }
        throw new RuntimeException("登录失败！！！");
    }
}
