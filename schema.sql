-- 1. Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS transitops;
USE transitops;

-- 2. Create Core Tables
CREATE TABLE IF NOT EXISTS vehicles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    max_capacity DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS drivers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    license_number VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS trips (
    id INT AUTO_INCREMENT PRIMARY KEY,
    source_location VARCHAR(100) NOT NULL,
    destination_location VARCHAR(100) NOT NULL,
    vehicle_id INT,
    driver_id INT,
    cargo_weight DOUBLE NOT NULL,
    route_distance DOUBLE NOT NULL,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id),
    FOREIGN KEY (driver_id) REFERENCES drivers(id)
);

-- 3. Seed Default Seed Data for Tests
INSERT INTO vehicles (id, name, max_capacity) 
VALUES (1, 'Tata Ultra', 7500.0)
ON DUPLICATE KEY UPDATE name = VALUES(name), max_capacity = VALUES(max_capacity);

INSERT INTO drivers (id, name, license_number) 
VALUES (1, 'Ramesh Kumar', 'MH-04-2026-00123')
ON DUPLICATE KEY UPDATE name = VALUES(name);