package ru.mikhailova.julia.SpringBootApp.сontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mikhailova.julia.SpringBootApp.model.User;
import ru.mikhailova.julia.SpringBootApp.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {
    private final UserService userService;

    public UserRestController (UserService userService) {
        this.userService = userService;
    }

    // authenticated user
    @GetMapping(value = "/user")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.getUserByUsername(userName);

        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
