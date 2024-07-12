INSERT INTO client (name, email) VALUES
('John Doe', 'john.doe@example.com'),
('Jane Smith', 'jane.smith@example.com'),
('Alice Johnson', 'alice.johnson@example.com'),
('Bob Brown', 'bob.brown@example.com'),
('Charlie Davis', 'charlie.davis@example.com'),
('Emily White', 'emily.white@example.com'),
('Frank Harris', 'frank.harris@example.com'),
('Grace Lee', 'grace.lee@example.com'),
('Henry Walker', 'henry.walker@example.com'),
('Isabella Scott', 'isabella.scott@example.com');

INSERT INTO planet (id, name, distance) VALUES
('MERCURY', 'Mercury', 77.3),
('VENUS', 'Venus', 41.4),
('MARS', 'Mars', 225.0),
('JUPITER', 'Jupiter', 628.7),
('SATURN', 'Saturn', 1275.0);

INSERT INTO ticket (client_id, planet_id, purchase_date) VALUES
(1, 'MERCURY', '2024-01-01'),
(2, 'VENUS', '2024-01-02'),
(3, 'MARS', '2024-01-03'),
(4, 'JUPITER', '2024-01-04'),
(5, 'SATURN', '2024-01-05'),
(6, 'MERCURY', '2024-01-06'),
(7, 'VENUS', '2024-01-07'),
(8, 'MARS', '2024-01-08'),
(9, 'JUPITER', '2024-01-09'),
(10, 'SATURN', '2024-01-10');
