USE  car_dealership
-- 1. Get all dealerships 
SELECT * 
FROM dealerships d
-- 2. Find all vehicles for a specific dealership 
SELECT v.*
FROM   vehicles v
JOIN   inventory i ON v.VIN = i.VIN
WHERE  i.dealership_id = 1;
-- 3. Find a car by VIN 
SELECT *
FROM   vehicles
WHERE  VIN = "1FTRW08L0XYZ12345";
-- 4. Find the dealership where a certain car is located, by VIN 
SELECT d.*
FROM   dealerships d
JOIN   inventory i ON d.id = i.dealership_id
WHERE  i.VIN = "1FTRW08L0XYZ12345";
-- 5. Find all Dealerships that have a certain car type (i.e. Red Ford Mustang) 
SELECT  d.*
FROM   dealerships d
JOIN   inventory i ON d.id = i.dealership_id
JOIN   vehicles   v ON v.VIN = i.VIN
WHERE  v.make  = 'Ford'
  AND  v.model = 'Mustang'
  AND  v.color = 'Red';
-- 6. Get all sales information for a specific dealer for a specific date RANGE
-- no date :)