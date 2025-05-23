//package ru.mikhailova.julia.SpringBootApp.сontroller;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import ru.mikhailova.julia.SpringBootApp.model.User;
//import ru.mikhailova.julia.SpringBootApp.service.UserService;
//
//@Controller
//@RequestMapping("/user")
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    private UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping
//    public String userProfile(Authentication authentication, Model model) {
//        String username = authentication.getName();
//        User user = userService.getUserByUsername(username);
//        if (user != null) {
//            model.addAttribute("user", user);
//            return "user-page";
//        } else {
//            return "redirect:/login";
//        }
//    }
//}