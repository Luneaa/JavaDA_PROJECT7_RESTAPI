package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
    @Mock
    private UserRepository userRepository;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @InjectMocks
    private UserController userController;

    @Test
    void home() {
        assertEquals("user/list", this.userController.home(model));
        verify(this.userRepository, times(1)).findAll();
    }

    @Test
    void addUser() {
        assertEquals("user/add", this.userController.addUser(mock(User.class)));
    }

    @Test
    void validate() {
        User user = new User();
        user.setPassword("test");

        assertEquals("redirect:/user/list", this.userController.validate(user, bindingResult, model));
        verify(this.userRepository, times(1)).save(any(User.class));
    }

    @Test
    void validateError() {
        User user = new User();
        user.setPassword("test");

        when(bindingResult.hasErrors()).thenReturn(true);

        assertEquals("user/add", this.userController.validate(user, bindingResult, model));
        verify(this.userRepository, times(0)).save(any(User.class));
    }

    @Test
    void showUpdateForm() {
        when(this.userRepository.findById(0)).thenReturn(Optional.of(mock(User.class)));

        assertEquals("user/update", this.userController.showUpdateForm(0, model));
    }

    @Test
    void showUpdateFormEmpty() {
        assertThrows(IllegalArgumentException.class, () -> this.userController.showUpdateForm(0, model));
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setPassword("test");

        assertEquals("redirect:/user/list", this.userController.updateUser(0, user, bindingResult, model));
        verify(this.userRepository, times(1)).save(any(User.class));
    }

    @Test
    void updateUserError() {
        User user = new User();
        user.setPassword("test");

        when(bindingResult.hasErrors()).thenReturn(true);

        assertEquals("user/update", this.userController.updateUser(0, user, bindingResult, model));
        verify(this.userRepository, times(0)).save(any(User.class));
    }

    @Test
    void deleteUser() {
        when(this.userRepository.findById(0)).thenReturn(Optional.of(mock(User.class)));

        assertEquals("redirect:/user/list", this.userController.deleteUser(0, model));
        verify(this.userRepository, times(1)).delete(any(User.class));
    }

    @Test
    void deleteUserError() {
        assertThrows(IllegalArgumentException.class, () -> this.userController.deleteUser(0, model));
    }
}
