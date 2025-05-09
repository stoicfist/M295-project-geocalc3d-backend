package ch.peter.ngo.geocalc3d.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {

    public List<Map<String, String>> getAllUsers() {
        // Dummy-Liste für die Abgabe
        return List.of(
                Map.of("username", "admin", "role", "ADMIN"),
                Map.of("username", "user1", "role", "USER"));
    }
}
