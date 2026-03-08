package cz.osu.swidemo.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "item_id", columnDefinition = "VARCHAR(36)")
    private String itemId;

    @Column(name = "item_name", nullable = false, length = 200)
    private String itemName;

    @JsonIgnore
    @ManyToMany(mappedBy = "items")
    private List<Order> orders = new ArrayList<>();

    public Item() {
    }

    public Item(String itemName) {
        this.itemName = itemName;
    }

    // Getters and Setters
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}

