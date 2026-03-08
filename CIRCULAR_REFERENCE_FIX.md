# Circular Reference Fix - JSON Serialization Issue

## Problem
When calling `/api/test/users`, the response showed infinite nested JSON due to circular references:
- User → orders (One-to-Many relationship)
- Order → user (Many-to-One relationship back)

This created an infinite loop where each User included their Orders, and each Order included their User back infinitely.

## Solution
Added `@JsonIgnore` annotation to the `user` field in the **Order** entity.

### What Changed
**File:** `Order.java`

```java
@JsonIgnore
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
private User user;
```

## How It Works
- `@JsonIgnore` tells Jackson (the JSON serializer) to **skip serializing** the `user` field when converting Order objects to JSON
- This breaks the circular reference chain
- The User entity still includes the `orders` collection, but each Order no longer includes the User back
- The relationship is preserved in the database - only the JSON output is affected

## Result
Now when you call `/api/test/users`, you get:
```json
[
  {
    "id": "550e8400-e29b-41d4-a716-446655440001",
    "username": "john_doe",
    "email": "john.doe@example.com",
    "age": 28,
    "firstName": "John",
    "lastName": "Doe",
    "password": "...",
    "orders": [
      {
        "orderId": "660e8400-...",
        "orderDate": "2026-01-15T10:30:00",
        "orderDescription": "Laptop and accessories",
        "items": []
        // Note: No "user" field here anymore!
      }
    ]
  }
]
```

## Alternative Solutions
1. **Use DTOs** - Create a UserDTO and OrderDTO that only include fields you want to expose
2. **Use @JsonBackReference/@JsonManagedReference** - Explicitly mark parent/child relationships
3. **Use @JsonIdentityInfo** - Include object IDs instead of full objects in circular references

The `@JsonIgnore` approach is simple and effective for this use case.

