package org.example.service;


import org.example.mapper.UserMapper;
import org.example.po.User;
import org.example.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper; // 需要创建一个 UserMapper 接口

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();

    }

    @Test
    public void testGetUserById() {
        when(userMapper.selectById(1L)).thenReturn(user);

        User foundUser = userService.getUserById(1L);

//        assertEquals("John Doe", foundUser.getName());
//        assertEquals(30, foundUser.getAge());
    }
}
