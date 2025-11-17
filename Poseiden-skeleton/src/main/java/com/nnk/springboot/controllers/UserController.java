package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.regex.Pattern;

/**
 * Controller for users
 * Admin only
 */
@Controller
@RequiredArgsConstructor
@RolesAllowed("ADMIN")
public class UserController {
    private final UserRepository userRepository;

    private static final String ATTRIBUTE_USERS = "users";

    private static final String REDIRECT_USER_LIST = "redirect:/user/list";

    private static final Pattern PASSWORD_VALIDATION = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,24}$");

    private static final String PASSWORD_INVALID_ERROR_MESSAGE = "Password needs to be at least 8 characters long and include an upper and a lower character a number and at least a symbol : @#$%^&+=!";

    /**
     * Displays the list of users
     * @param model Spring model
     * @return user list url
     */
    @RequestMapping("/user/list")
    public String home(Model model)
    {
        model.addAttribute(ATTRIBUTE_USERS, userRepository.findAll());
        return "user/list";
    }

    /**
     * Displays the add form for users
     * @param bid _
     * @return user add form url
     */
    @GetMapping("/user/add")
    public String addUser(User bid) {
        return "user/add";
    }

    /**
     * Validates and add a user and redirects to list if valid
     * @param user user to validate and add
     * @param result _
     * @param model _
     * @return result url
     */
    @PostMapping("/user/validate")
    public String validate(@Valid User user, BindingResult result, Model model) {
        // Check if password is valid
        if (!isPasswordValid(user.getPassword())){
            result.addError(new FieldError("user", "password", PASSWORD_INVALID_ERROR_MESSAGE));
        }

        // Check if user is valid
        if (!result.hasErrors()) {
            // Encode password
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            // Set to -1 to ensure new entity is created
            user.setId(-1);
            userRepository.save(user);
            model.addAttribute(ATTRIBUTE_USERS, userRepository.findAll());
            return REDIRECT_USER_LIST;
        }
        return "user/add";
    }

    /**
     * Displays the update form for users
     * @param id id of the user to update
     * @param model spring model
     * @return url of the user update form
     */
    @GetMapping("/user/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // Check that user exists or throw exception
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        // Empty password to avoid returning it
        user.setPassword("");
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Validates and updates a user
     * @param id id of the user to update
     * @param user updated user entity
     * @param result _
     * @param model _
     * @return result url
     */
    @PostMapping("/user/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid User user,
                             BindingResult result, Model model) {
        // Check if password is valid
        if (!isPasswordValid(user.getPassword())){
            result.addError(new FieldError("user", "password", PASSWORD_INVALID_ERROR_MESSAGE));
        }

        // Check if user is valid
        if (result.hasErrors()) {
            return "user/update";
        }

        // Encode password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        // Use url id to avoid a user changing the id
        user.setId(id);
        userRepository.save(user);
        model.addAttribute(ATTRIBUTE_USERS, userRepository.findAll());
        return REDIRECT_USER_LIST;
    }

    /**
     * Deletes a user based on its id
     * @param id id of the user to delete
     * @param model _
     * @return result url
     */
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        // Find user or throw exception
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        // Delete user
        userRepository.delete(user);
        model.addAttribute(ATTRIBUTE_USERS, userRepository.findAll());
        return REDIRECT_USER_LIST;
    }

    /**
     * Checks if the given password is valid
     * @param password password to check
     * @return true if valid, false if not
     */
    private boolean isPasswordValid(String password) {
        return password != null && PASSWORD_VALIDATION.matcher(password).matches();
    }
}
