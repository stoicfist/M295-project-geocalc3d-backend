package ch.peter.ngo.geocalc3d.controller;

import ch.peter.ngo.geocalc3d.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<Map<String, String>> getAllUsers() {
        return userService.getAllUsers();
    }
}
