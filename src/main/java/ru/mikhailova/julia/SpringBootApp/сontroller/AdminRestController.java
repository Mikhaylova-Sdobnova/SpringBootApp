package ru.mikhailova.julia.SpringBootApp.сontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mikhailova.julia.SpringBootApp.model.Role;
import ru.mikhailova.julia.SpringBootApp.model.User;
import ru.mikhailova.julia.SpringBootApp.service.RoleService;
import ru.mikhailova.julia.SpringBootApp.service.UserService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminRestController (UserService userService, RoleService roleService) {

        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        final List<User> users = userService.findAll();

        return users != null &&  !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser (@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);
        return user != null
                ? new ResponseEntity<>(user, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<?> addUser (@RequestBody User model) {
        userService.save(model);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<?> editUser (@PathVariable(name = "id") Long id,  @RequestBody User model) {
        final boolean updated = userService.updateUser(model);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        final boolean deleted = userService.deleteById(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        final List<Role> roles = roleService.getAllRoles();

        return roles != null &&  !roles.isEmpty()
                ? new ResponseEntity<>(roles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
