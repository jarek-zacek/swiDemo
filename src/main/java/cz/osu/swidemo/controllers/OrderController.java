package cz.osu.swidemo.controllers;

import cz.osu.swidemo.dto.ItemDTO;
import cz.osu.swidemo.entities.Item;
import cz.osu.swidemo.entities.Order;
import cz.osu.swidemo.entities.User;
import cz.osu.swidemo.repositories.ItemRepository;
import cz.osu.swidemo.repositories.OrderRepository;
import cz.osu.swidemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            // Validate and fetch the user
            Optional<User> userOptional = userRepository.findById(orderRequest.getUserId());
            if (!userOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("User not found with ID: " + orderRequest.getUserId());
            }

            // Create new order
            Order order = new Order();
            order.setUser(userOptional.get());
            order.setOrderDate(orderRequest.getOrderDate());
            order.setOrderDescription(orderRequest.getOrderDescription());

            // Handle items if provided
            if (orderRequest.getItemIds() != null && !orderRequest.getItemIds().isEmpty()) {
                List<Item> items = new ArrayList<>();
                for (String itemId : orderRequest.getItemIds()) {
                    Optional<Item> itemOptional = itemRepository.findById(itemId);
                    if (itemOptional.isPresent()) {
                        items.add(itemOptional.get());
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("Item not found with ID: " + itemId);
                    }
                }
                order.setItems(items);
            }

            // Save the order
            orderRepository.save(order);

            return ResponseEntity.status(HttpStatus.CREATED).body("OK");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating order: " + e.getMessage());
        }
    }

    @PostMapping("/items")
    public ResponseEntity<?> getItemsByOrderId(@RequestBody OrderItemsRequest request) {
        if (request == null || request.getOrderId() == null || request.getOrderId().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("orderId is required");
        }

        Optional<Order> orderOptional = orderRepository.findById(request.getOrderId());
        if (!orderOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Order not found with ID: " + request.getOrderId());
        }

        List<ItemDTO> itemDTOs = new ArrayList<>();
        for (Item item : orderOptional.get().getItems()) {
            itemDTOs.add(new ItemDTO(item.getItemId(), item.getItemName()));
        }

        return ResponseEntity.ok(itemDTOs);
    }

    // Inner class for request body
    public static class OrderRequest {
        private String userId;
        private java.time.LocalDateTime orderDate;
        private String orderDescription;
        private List<String> itemIds;

        public OrderRequest() {
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public java.time.LocalDateTime getOrderDate() {
            return orderDate;
        }

        public void setOrderDate(java.time.LocalDateTime orderDate) {
            this.orderDate = orderDate;
        }

        public String getOrderDescription() {
            return orderDescription;
        }

        public void setOrderDescription(String orderDescription) {
            this.orderDescription = orderDescription;
        }

        public List<String> getItemIds() {
            return itemIds;
        }

        public void setItemIds(List<String> itemIds) {
            this.itemIds = itemIds;
        }
    }

    public static class OrderItemsRequest {
        private String orderId;

        public OrderItemsRequest() {
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
    }
}
