package ru.ald.officebooking.user.controller;

import org.springframework.web.bind.annotation.*;
import ru.ald.officebooking.user.model.User;
import ru.ald.officebooking.user.service.UserService;

import java.util.List;

import static ru.ald.officebooking.common.ApiPaths.ID_PATH;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public void createUser() {
        return userService.createUser();
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(ID_PATH)
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PutMapping(ID_PATH)
    public User updateUser(@PathVariable Long id) {
        return userService.updateUser(id);
    }

    @DeleteMapping(ID_PATH)
    public User deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
