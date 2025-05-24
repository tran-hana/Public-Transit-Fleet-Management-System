
DROP DATABASE IF EXISTS ptfms;

CREATE DATABASE ptfms;

USE ptfms;

CREATE TABLE `user` (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(50) NOT NULL,
  role VARCHAR(50) NOT NULL
);

CREATE TABLE vehicle (
  id INT NOT NULL PRIMARY KEY,
  type VARCHAR(30) NOT NULL,
  fuel_type VARCHAR(20) NOT NULL,
  max_passengers INT NOT NULL,
  route INT NOT NULL,
  consumption_rate DECIMAL(8,2) NOT NULL
);

CREATE TABLE vehicle_assign (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  vehicle_id INT,
  operator_id INT NOT NULL,
  CONSTRAINT fk_assign_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle(id),
  CONSTRAINT fk_assign_operator FOREIGN KEY (operator_id) REFERENCES `user`(id)
);

CREATE TABLE station (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE vehicle_tracking (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  assign_id INT NOT NULL,
  station_id INT NOT NULL,
  arrival_time DATETIME,
  departure_time DATETIME,
  FOREIGN KEY (assign_id) REFERENCES vehicle_assign(id),
  FOREIGN KEY (station_id) REFERENCES station(id)
);

CREATE TABLE break_time (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  assign_id INT NOT NULL,
  start_time DATETIME NOT NULL,
  end_time DATETIME,
  FOREIGN KEY (assign_id) REFERENCES vehicle_assign(id)
);

CREATE TABLE consumption (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  vehicle_id INT,
  distance_travelled DECIMAL(8,2) NOT NULL,
  actual_consumption DECIMAL(8,2) NOT NULL,
  CONSTRAINT fk_consumption_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)
);

CREATE TABLE component (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  vehicle_id INT ,
  type VARCHAR(50) NOT NULL,
  usage_hours INT NOT NULL,
  CONSTRAINT fk_component_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)
);


CREATE TABLE maintenance (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  component_id INT ,
  date DATETIME NOT NULL,
  cost_cents INT NOT NULL DEFAULT 0,
  CONSTRAINT fk_maintenance_component FOREIGN KEY (component_id) REFERENCES component(id)
);

-- Sample Data

INSERT INTO `user` (name, email, password, role) VALUES ('Alice Smith', 'alice@example.com', 'pass123', 'Transit Manager');
INSERT INTO `user` (name, email, password, role) VALUES ('Bob Chen', 'bob@example.com', 'bobpass', 'Transit Manager');
INSERT INTO `user` (name, email, password, role) VALUES ('Carol Lin', 'carol@example.com', 'carol123', 'Operator');
INSERT INTO `user` (name, email, password, role) VALUES ('Dan Park', 'dan@example.com', 'danpass', 'Transit Manager');
INSERT INTO `user` (name, email, password, role) VALUES ('Eva Liu', 'eva@example.com', 'evapass', 'Operator');
INSERT INTO `user` (name, email, password, role) VALUES ('Fred Lynton', 'fred@example.com', 'fredpass', 'Operator');
INSERT INTO `user` (name, email, password, role) VALUES ('George Brown', 'george@example.com', 'georgepass', 'Operator');


INSERT INTO vehicle (id, type, fuel_type, max_passengers, route, consumption_rate) VALUES (1001, 'Diesel Bus', 'Diesel', 50, 23, 0.4);
INSERT INTO vehicle (id, type, fuel_type, max_passengers, route, consumption_rate) VALUES (1002, 'Electric Light Rail', 'Electric', 100, 101, 0.2);
INSERT INTO vehicle (id, type, fuel_type, max_passengers, route, consumption_rate) VALUES (1003, 'Diesel-Electric Train', 'Hybrid', 200, 201, 0.3);
INSERT INTO vehicle (id, type, fuel_type, max_passengers, route, consumption_rate) VALUES (1004, 'Electric Light Rail', 'Electric', 100, 102, 0.2);
INSERT INTO vehicle (id, type, fuel_type, max_passengers, route, consumption_rate) VALUES (1005, 'Diesel Bus', 'Diesel', 35, 75, 0.09);


INSERT INTO vehicle_assign (vehicle_id, operator_id) VALUES (1001, 2);
INSERT INTO vehicle_assign (vehicle_id, operator_id) VALUES (1002, 3);
INSERT INTO vehicle_assign (vehicle_id, operator_id) VALUES (1003, 5);
INSERT INTO vehicle_assign (vehicle_id, operator_id) VALUES (1004, 6);
INSERT INTO vehicle_assign (vehicle_id, operator_id) VALUES (1005, 7);


INSERT INTO station (name) VALUES ('Baseline');
INSERT INTO station (name) VALUES ('Bayview');
INSERT INTO station (name) VALUES ('Pimisi');
INSERT INTO station (name) VALUES ('Lyon');
INSERT INTO station (name) VALUES ('Rideau');
INSERT INTO station (name) VALUES ('Hurdman');
INSERT INTO station (name) VALUES ('Blair');


INSERT INTO vehicle_tracking (assign_id, station_id, arrival_time, departure_time) VALUES (1, 1, '2025-03-27 08:00:00', '2025-03-27 08:00:30');
INSERT INTO vehicle_tracking (assign_id, station_id, arrival_time, departure_time) VALUES (1, 2, '2025-03-27 08:05:00', '2025-03-27 08:06:00');
INSERT INTO vehicle_tracking (assign_id, station_id, arrival_time, departure_time) VALUES (1, 3, '2025-03-27 08:10:00', '2025-03-27 08:10:50');
INSERT INTO vehicle_tracking (assign_id, station_id, arrival_time, departure_time) VALUES (2, 7, '2025-03-27 09:00:00', '2025-03-27 09:02:00');
INSERT INTO vehicle_tracking (assign_id, station_id, arrival_time, departure_time) VALUES (2, 6, '2025-03-27 09:08:00', '2025-03-27 09:10:00');
INSERT INTO vehicle_tracking (assign_id, station_id, arrival_time, departure_time) VALUES (2, 5, '2025-03-27 09:16:00', '2025-03-27 09:18:00');
INSERT INTO vehicle_tracking (assign_id, station_id, arrival_time, departure_time) VALUES (3, 3, '2025-03-27 10:00:00', '2025-03-27 10:20:00');
INSERT INTO vehicle_tracking (assign_id, station_id, arrival_time, departure_time) VALUES (3, 4, '2025-03-27 10:18:00', '2025-03-27 10:20:00');
INSERT INTO vehicle_tracking (assign_id, station_id, arrival_time, departure_time) VALUES (4, 4, '2025-03-27 11:00:00', '2025-03-27 11:03:00');
INSERT INTO vehicle_tracking (assign_id, station_id, arrival_time, departure_time) VALUES (4, 5, '2025-03-27 11:10:00', '2025-03-27 11:10:50');
INSERT INTO vehicle_tracking (assign_id, station_id, arrival_time, departure_time) VALUES (5, 5, '2025-03-27 12:12:00', '2025-03-27 12:15:00');
INSERT INTO vehicle_tracking (assign_id, station_id, arrival_time, departure_time) VALUES (5, 6, '2025-03-27 12:25:00', '2025-03-27 12:26:00');


INSERT INTO break_time (assign_id, start_time, end_time) VALUES (1, '2025-03-27 08:30:00', '2025-03-27 08:45:00');
INSERT INTO break_time (assign_id, start_time, end_time) VALUES (2, '2025-03-27 09:30:00', '2025-03-27 09:50:00');
INSERT INTO break_time (assign_id, start_time, end_time) VALUES (3, '2025-03-27 10:30:00', '2025-03-27 10:40:00');
INSERT INTO break_time (assign_id, start_time, end_time) VALUES (4, '2025-03-27 11:30:00', '2025-03-27 11:50:00');
INSERT INTO break_time (assign_id, start_time, end_time) VALUES (5, '2025-03-27 12:30:00', '2025-03-27 12:55:00');


-- Vehicle 1001: Diesel Bus, base rate 0.08
INSERT INTO consumption (vehicle_id, distance_travelled, actual_consumption) VALUES (1001, 95.0, 8.3);   -- rate ~0.087
-- Vehicle 1002: Electric Light Rail, base rate 0.05
INSERT INTO consumption (vehicle_id, distance_travelled, actual_consumption) VALUES (1002, 200.0, 5.4);   -- rate ~0.049
-- Vehicle 1003: Diesel-Electric Train, base rate 0.10
INSERT INTO consumption (vehicle_id, distance_travelled, actual_consumption) VALUES (1003, 140.0, 15.0); -- rate ~0.107
-- Vehicle 1004: Electric Light Rail, base rate 0.04
INSERT INTO consumption (vehicle_id, distance_travelled, actual_consumption) VALUES (1004, 250.0, 5.0);  -- rate = 0.050
-- Vehicle 1005: Diesel Bus, base rate 0.09
INSERT INTO consumption (vehicle_id, distance_travelled, actual_consumption) VALUES (1005, 120.0, 11.4); -- rate ~0.095


-- Vehicle 1001 - Diesel Bus
INSERT INTO component (vehicle_id, type, usage_hours) VALUES (1001, 'brake', 180);
INSERT INTO component (vehicle_id, type, usage_hours) VALUES (1001, 'wheels', 30);
INSERT INTO component (vehicle_id, type, usage_hours) VALUES (1001, 'engine', 300);

-- Vehicle 1002 - Electric Light Rail
INSERT INTO component (vehicle_id, type, usage_hours) VALUES (1002, 'brake', 210);
INSERT INTO component (vehicle_id, type, usage_hours) VALUES (1002, 'wheels', 270);
INSERT INTO component (vehicle_id, type, usage_hours) VALUES (1002, 'catenary', 250);

-- Vehicle 1003 - Diesel-Electric Train
INSERT INTO component (vehicle_id, type, usage_hours) VALUES (1003, 'brake', 260);  -- larger than 250, needs maintenance
INSERT INTO component (vehicle_id, type, usage_hours) VALUES (1003, 'wheels', 350);
INSERT INTO component (vehicle_id, type, usage_hours) VALUES (1003, 'engine', 420);

-- Vehicle 1004 - Electric Light Rail
INSERT INTO component (vehicle_id, type, usage_hours) VALUES (1004, 'brake', 120);
INSERT INTO component (vehicle_id, type, usage_hours) VALUES (1004, 'wheels', 405);  -- larger than 400, needs maintenance
INSERT INTO component (vehicle_id, type, usage_hours) VALUES (1004, 'catenary', 280);

-- Vehicle 1005 - Diesel Bus
INSERT INTO component (vehicle_id, type, usage_hours) VALUES (1005, 'brake', 200);
INSERT INTO component (vehicle_id, type, usage_hours) VALUES (1005, 'wheels', 390);
INSERT INTO component (vehicle_id, type, usage_hours) VALUES (1005, 'engine', 520);  -- larger than 500, needs maintenance


INSERT INTO maintenance (component_id, date, cost_cents) VALUES (15, '2025-02-03 10:00:00', 90000);
INSERT INTO maintenance (component_id, date, cost_cents) VALUES (9,  '2025-02-10 10:00:00', 90000);
INSERT INTO maintenance (component_id, date, cost_cents) VALUES (14, '2025-02-14 10:00:00', 55000);
INSERT INTO maintenance (component_id, date, cost_cents) VALUES (8,  '2025-02-19 10:00:00', 55000);
INSERT INTO maintenance (component_id, date, cost_cents) VALUES (3,  '2025-02-24 10:00:00', 90000);
INSERT INTO maintenance (component_id, date, cost_cents) VALUES (7,  '2025-02-28 10:00:00', 45000);
INSERT INTO maintenance (component_id, date, cost_cents) VALUES (13, '2025-02-28 10:00:00', 45000);
INSERT INTO maintenance (component_id, date, cost_cents) VALUES (6,  '2025-03-03 10:00:00', 35000);
INSERT INTO maintenance (component_id, date, cost_cents) VALUES (12, '2025-03-01 10:00:00', 35000);
INSERT INTO maintenance (component_id, date, cost_cents) VALUES (5,  '2025-03-01 10:00:00', 55000);
INSERT INTO maintenance (component_id, date, cost_cents) VALUES (4,  '2025-03-07 10:00:00', 45000);
INSERT INTO maintenance (component_id, date, cost_cents) VALUES (11, '2025-03-18 10:00:00', 45000);
INSERT INTO maintenance (component_id, date, cost_cents) VALUES (1,  '2025-03-12 10:00:00', 45000);
INSERT INTO maintenance (component_id, date, cost_cents) VALUES (10, '2025-03-28 10:00:00', 45000);
INSERT INTO maintenance (component_id, date, cost_cents) VALUES (2,  '2025-03-30 10:00:00', 50000);
INSERT INTO maintenance (component_id, date) VALUES (7,  '2025-04-18 10:00:00');  -- Maintenance scheduled for future