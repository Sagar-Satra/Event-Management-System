package com.eventmanagement.eventapp.controllers;

import com.eventmanagement.eventapp.daos.UserDao;
import com.eventmanagement.eventapp.dto.ProfileDTO;
import com.eventmanagement.eventapp.dto.UserLoginDTO;
import com.eventmanagement.eventapp.dto.UserRegisterDTO;
import com.eventmanagement.eventapp.models.User;
import com.eventmanagement.eventapp.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// User controller for User related operations
@RequestMapping("/api/users")
@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    // API for validating new user registration inputs and creating a new user account
    @PostMapping("/register")
    public String createUser(@ModelAttribute @Valid UserRegisterDTO userRegisterDTO, BindingResult result, RedirectAttributes redirectAttributes ){
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("validationError", result.getAllErrors());
            return "redirect:/register";
        }
        try{
            userService.register(userRegisterDTO);
            redirectAttributes.addFlashAttribute("message", "User registered successfully");
            return "redirect:/login";
        } catch (IllegalArgumentException e){
            redirectAttributes.addFlashAttribute("error" , "Error registering a new user: " + e.getMessage());
            return "redirect:/register";
        }
    }

    // API for validating the login information and logging in the user with email and password
    @PostMapping("/login")
    public String login(@ModelAttribute @Valid UserLoginDTO userLoginDTO, BindingResult result, HttpSession session, ModelMap map, RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            map.addAttribute("validationError", result.getFieldErrors());
            return "login";
        }
        try{
            userService.login(userLoginDTO, session);
            redirectAttributes.addFlashAttribute("message" , "User logged in successfully");
            return "redirect:/home";
        } catch (IllegalAccessException e){
            redirectAttributes.addFlashAttribute("error" , "Error Login: " + e.getMessage());
            return "redirect:/login";
        }
    }

    // api for getting a user by ID
    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id , ModelMap map){
        try{
            User user = userService.getUserById(id);
            System.out.println("inside get id " + user.getId());
            map.addAttribute("user", user);
            return "redirect:/user";
        } catch (Exception e){
            map.addAttribute("error" , e.getMessage());
            return "redirect:/error";
        }
    }

    // api for updating the profile of a user
    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id , @ModelAttribute  @Valid ProfileDTO updatedUser , BindingResult result, HttpSession session , ModelMap map, RedirectAttributes redirectAttributes ){
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("validationError", result.getAllErrors());
            return "redirect:/home/profile/edit";
        }
        try {
            userService.updateUser(updatedUser, id , session);
            redirectAttributes.addFlashAttribute("message" , "User details updated successfully");
            return "redirect:/home/profile";
        } catch (Exception e){
            map.addAttribute("error" , "Error in updating the user - " + e.getMessage());
            return "error";
        }
    }
}
