package cz.osu.swidemo.dto;

import java.time.LocalDateTime;

public class OrderDTO {
    private String orderId;
    private String userId;
    private String username;
    private LocalDateTime orderDate;
    private String orderDescription;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String userId, String username, LocalDateTime orderDate, String orderDescription) {
        this.orderId = orderId;
        this.userId = userId;
        this.username = username;
        this.orderDate = orderDate;
        this.orderDescription = orderDescription;
    }

    // Getters and Setters
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }
}

