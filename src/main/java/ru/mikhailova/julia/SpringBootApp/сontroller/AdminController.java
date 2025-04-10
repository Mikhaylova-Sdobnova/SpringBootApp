package ru.mikhailova.julia.SpringBootApp.сontroller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mikhailova.julia.SpringBootApp.model.Role;
import ru.mikhailova.julia.SpringBootApp.model.User;
import ru.mikhailova.julia.SpringBootApp.service.RoleService;
import ru.mikhailova.julia.SpringBootApp.service.UserService;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String allUsers(Model model, Authentication authentication) {
        List<User> users = userService.findAll();
        String username = authentication.getName();
        User admin = userService.getUserByUsername(username);
        if (admin != null) {
            model.addAttribute("admin", admin);
            model.addAttribute("users", users);

            return "admin-page";
        } else {

            return "redirect:/login";
        }
    }

    @GetMapping("/new")
    public String createUserForm(@ModelAttribute("user") User user,
                                 Authentication authentication, Model model) {
        String username = authentication.getName();
        User admin = userService.getUserByUsername(username);
        List<Role> roles = roleService.getAllRoles();

        if (admin != null) {
            model.addAttribute("admin", admin);
            model.addAttribute("allRoles", roles);

            return "add-new-user";
        } else {

            return "redirect:/login";
        }
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") @Valid User user,
                             @RequestParam("authorities") List<Long> values,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/admin";
        }
        Set<Role> roleSet = roleService.getSetOfRoles(values);
        user.setRoles(roleSet);
        userService.save(user);

        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String editUserForm(@RequestParam("id") Long id, Model model) {
        User userById = userService.findById(id);
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("allRoles", roles);
        if (userById != null) {
            model.addAttribute("user", userById);
            return "edit-user";
        } else {
            return "redirect:/admin";
        }
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") @Valid User user,
                           @RequestParam("authorities") List<Long> values,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-user";
        }

        if (values != null) {
            Set<Role> roleSet = roleService.getSetOfRoles(values);
            user.setRoles(roleSet);
        }
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}