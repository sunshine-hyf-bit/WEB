package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.example.mapper.UserMapper;
import org.example.po.User;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User getUserById(Long id) {
        return this.getById(id);
    }
}
