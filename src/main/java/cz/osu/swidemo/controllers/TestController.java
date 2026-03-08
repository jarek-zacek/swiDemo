package cz.osu.swidemo.controllers;


import cz.osu.swidemo.entities.Order;
import cz.osu.swidemo.entities.User;
import cz.osu.swidemo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cz.osu.swidemo.repositories.UserRepository;

// Add UUID for unique usernames.
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public String test() {
        User user = new User();
        user.setUsername("testuser-" + UUID.randomUUID());
        user.setPassword("password123");
        user.setAge(25);
        user.setEmail("test@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");

        userRepository.save(user);

        return "Hello from Test Controller";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/create-user-with-orders")
    public String createUserWithOrders() {
        // Create a new user
        User user = new User();
        user.setUsername("user-" + UUID.randomUUID().toString().substring(0, 8));
        user.setPassword("$2a$10$slYQmyNdGzin7olVN3p5be4nxQjV2d9dGvQGAlt28WjLdMZGd7rOG");

        Random random = new Random();
        user.setAge(18 + random.nextInt(40));
        user.setEmail(user.getUsername() + "@example.com");

        user.setFirstName("John");
        user.setLastName("Wilson");

        // Save the user first
        user = userRepository.save(user);

        // First order
        Order order1 = new Order();
        order1.setUser(user);
        order1.setOrderDate(LocalDateTime.now().minusDays(random.nextInt(30)));
        order1.setOrderDescription("Software licenses");
        orderRepository.save(order1);

        // Second order
        Order order2 = new Order();
        order2.setUser(user);
        order2.setOrderDate(LocalDateTime.now().minusDays(random.nextInt(30)));
        order2.setOrderDescription("Mobile devices");
        orderRepository.save(order2);

        return "User created with ID: " + user.getId() + " and 2 orders added";
    }

}
