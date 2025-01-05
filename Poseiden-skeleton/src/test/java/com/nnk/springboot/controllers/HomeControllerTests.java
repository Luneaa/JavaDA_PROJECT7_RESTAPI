package com.nnk.springboot.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTests {
    @Mock
    private Model model;

    @InjectMocks
    private HomeController homeController;

    @Test
    void home() {
        assertEquals("home", this.homeController.home(model));
    }

    @Test
    void adminHome() {
        assertEquals("redirect:/bidList/list", this.homeController.adminHome(model));
    }
}
