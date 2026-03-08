# Circular Reference Fix - Many-to-Many Relationship

## Problem (Final Issue)
Even after fixing the User ↔ Order circular reference, there was still an infinite loop in the many-to-many relationship:
- Order → items (Many-to-Many)
- Item → orders (Inverse many-to-many)

This created: Order → Item → Order → Item → ... (infinite)

## Solution
Added `@JsonIgnore` annotations to **both sides** of bidirectional relationships:

### Fixed Entities

**1. Order.java** (already fixed)
```java
@JsonIgnore
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "user_id", nullable = false)
private User user;
```

**2. Item.java** (now fixed)
```java
@JsonIgnore
@ManyToMany(mappedBy = "items")
private List<Order> orders = new ArrayList<>();
```

## Complete Circular Reference Prevention Strategy

Your application now has this relationship structure:

```
User
├── orders (List<Order>) ✓ Serialized in JSON
│   └── user (ignored) ✗ NOT serialized - prevents User→Order→User loop
│
└── (no direct items reference)

Item
├── itemName ✓ Serialized in JSON
└── orders (ignored) ✗ NOT serialized - prevents Item→Order→Item loop

Order (Middle entity)
├── orderId ✓ Serialized in JSON
├── orderDate ✓ Serialized in JSON
├── orderDescription ✓ Serialized in JSON
├── user (ignored) ✗ NOT serialized
└── items (List<Item>) ✓ Serialized in JSON
```

## Final Result

When you call `/api/test/users`, you now get clean JSON:

```json
[
  {
    "id": "550e8400-...",
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
        "items": [
          {
            "itemId": "770e8400-...",
            "itemName": "Dell XPS 15 Laptop"
            // Note: No "orders" field here - prevents circular reference!
          }
        ]
      }
    ]
  }
]
```

## Why This Works

- `@JsonIgnore` tells Jackson to **skip** serializing that field
- The database relationships are **completely preserved** - no data loss
- Only the JSON output is affected
- Users include Orders, Orders include Items, but Items don't include Orders back

## Key Principle

In bidirectional relationships, you want to **break the cycle** by ignoring one direction in JSON serialization. Choose the direction that makes less sense to serialize (usually the "back-reference" or "inverse" side).

In your case:
- ✓ Makes sense: User → Orders → Items
- ✗ Doesn't make sense: Items → Orders → Users (and repeats)

Therefore, we ignore:
- Order.user (the back-reference from Order to User)
- Item.orders (the inverse side of the many-to-many)

