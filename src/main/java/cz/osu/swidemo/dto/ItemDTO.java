package cz.osu.swidemo.dto;

public class ItemDTO {
    private String itemId;
    private String itemName;

    public ItemDTO() {
    }

    public ItemDTO(String itemId, String itemName) {
        this.itemId = itemId;
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
}

