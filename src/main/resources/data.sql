-- Seed data for users table
INSERT INTO users (id, username, password, age, email, first_name, last_name) VALUES
('550e8400-e29b-41d4-a716-446655440001', 'john_doe', '$2a$10$slYQmyNdGzin7olVN3p5be4nxQjV2d9dGvQGAlt28WjLdMZGd7rOG', 28, 'john.doe@example.com', 'John', 'Doe'),
('550e8400-e29b-41d4-a716-446655440002', 'jane_smith', '$2a$10$slYQmyNdGzin7olVN3p5be4nxQjV2d9dGvQGAlt28WjLdMZGd7rOG', 34, 'jane.smith@example.com', 'Jane', 'Smith'),
('550e8400-e29b-41d4-a716-446655440003', 'bob_wilson', '$2a$10$slYQmyNdGzin7olVN3p5be4nxQjV2d9dGvQGAlt28WjLdMZGd7rOG', 45, 'bob.wilson@example.com', 'Bob', 'Wilson'),
('550e8400-e29b-41d4-a716-446655440004', 'alice_johnson', '$2a$10$slYQmyNdGzin7olVN3p5be4nxQjV2d9dGvQGAlt28WjLdMZGd7rOG', 31, 'alice.johnson@example.com', 'Alice', 'Johnson'),
('550e8400-e29b-41d4-a716-446655440005', 'charlie_brown', '$2a$10$slYQmyNdGzin7olVN3p5be4nxQjV2d9dGvQGAlt28WjLdMZGd7rOG', 26, 'charlie.brown@example.com', 'Charlie', 'Brown');

-- Seed data for orders table
INSERT INTO orders (order_id, user_id, order_date, order_description) VALUES
('660e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440001', '2026-01-15 10:30:00', 'Laptop and accessories'),
('660e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440001', '2026-02-20 14:45:00', 'Office supplies'),
('660e8400-e29b-41d4-a716-446655440003', '550e8400-e29b-41d4-a716-446655440002', '2026-01-25 09:15:00', 'Software licenses'),
('660e8400-e29b-41d4-a716-446655440004', '550e8400-e29b-41d4-a716-446655440002', '2026-02-10 16:20:00', 'Cloud storage subscription'),
('660e8400-e29b-41d4-a716-446655440005', '550e8400-e29b-41d4-a716-446655440003', '2026-03-01 11:00:00', 'Network equipment'),
('660e8400-e29b-41d4-a716-446655440006', '550e8400-e29b-41d4-a716-446655440004', '2026-02-28 13:30:00', 'Mobile devices'),
('660e8400-e29b-41d4-a716-446655440007', '550e8400-e29b-41d4-a716-446655440005', '2026-03-05 15:45:00', 'Training materials');

