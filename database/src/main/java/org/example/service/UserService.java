package org.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.po.User;


public interface UserService extends IService<User> {
    User getUserById(Long id);
}