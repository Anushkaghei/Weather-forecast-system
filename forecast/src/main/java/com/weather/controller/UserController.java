package com.weather.controller;

import com.weather.dao.UserDAO;
import com.weather.model.User;
import org.springframework.ui.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

     @GetMapping("/report")
    public String showreport() {
        return "report";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/dashboard")
    public String showDashboardPage() {
        return "dashboard";
    }

    @GetMapping("/delete")
    public String deleteAccount() {
        return "delete";
    }

    @GetMapping("/update")
    public String updateProfile(Model model) {
        model.addAttribute("user", new User());
        return "update";
    }

    @GetMapping("/weather")
    public String weatherforecast() {
        return "weather";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("email") String email) {
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);

        boolean registrationSuccess = userDAO.registerUser(newUser);
        if (registrationSuccess) {
            return "redirect:/login";
        } else {
            // Handle registration failure
            return "register";
        }
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password) {
        User user = userDAO.validateUser(username, password);
        if (user != null) {
            // If user is validated, create a session and redirect to dashboard
            return "redirect:/dashboard";
        } else {
            // If validation fails, redirect back to login page
            return "login";
        }
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@RequestParam("email") String email,
                                @RequestParam("password") String password,
                                @RequestParam("newUsername") String newUsername,
                                @RequestParam("newEmail") String newEmail,
                                @RequestParam("newPassword") String newPassword) {
        // Authenticate the user
        User user = userDAO.validateUser(email, password);
        if (user != null) {
            // Set new details
            user.setUsername(newUsername);
            user.setEmail(newEmail);
            user.setPassword(newPassword);
            // Update user in the database
            boolean updateSuccess = userDAO.updateUser(user);
            if (updateSuccess) {
                return "redirect:/dashboard";
            } else {
                return "error";
            }
        } else {
            // Authentication failed, redirect to login page
            return "redirect:/login";
        }
    }

    @PostMapping("/deleteAccount")
    public String deleteAccount(@RequestParam("email") String email) {
        userDAO.deleteUserByEmail(email);
        return "redirect:/register";
    }
    
    
}
