package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoginController loginController;

    @Test
    void login()
    {
        assertEquals("login", this.loginController.login().getViewName());
    }

    @Test
    void getAllUserArticles() {
        assertEquals("user/list", this.loginController.getAllUserArticles().getViewName());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void error() {
        assertEquals("errors/403", this.loginController.error().getViewName());
    }
}
