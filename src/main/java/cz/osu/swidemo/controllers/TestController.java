package cz.osu.swidemo.controllers;


import cz.osu.swidemo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cz.osu.swidemo.repositories.UserRepository;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String test() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");
        user.setAge(25);
        user.setEmail("test@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");

        userRepository.save(user);

        return "Hello from Test Controller";


    }
}
