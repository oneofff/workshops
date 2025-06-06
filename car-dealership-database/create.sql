DROP DATABASE IF EXISTS car_dealersip;

CREATE DATABASE IF NOT EXISTS car_dealersip;

USE car_dealersip;

# ---------------------------------------------------------------------- #
# Tables                                                                 #
# ---------------------------------------------------------------------- #
# ---------------------------------------------------------------------- #
# Add table "dealerships"                                                 #
# ---------------------------------------------------------------------- #

CREATE TABLE dealerships (
	id int NOT NULL AUTO_INCREMENT,
	name varchar(50) NOT NULL,
	address varchar(50) NOT NULL,
	phone varchar (12),
	PRIMARY KEY (id)
);


# ---------------------------------------------------------------------- #
# Add table "vehicles"                                                 #
# ---------------------------------------------------------------------- #

CREATE TABLE vehicles(
	VIN varchar(250) NOT NULL UNIQUE,
	make varchar(50) NOT NULL,
	model varchar(50) NOT NULL,
	color varchar(50),
	sold TINYINT(1) DEFAULT 0,
	PRIMARY KEY (VIN)
);

# ---------------------------------------------------------------------- #
# Add table "inventory"                                                 #
# ---------------------------------------------------------------------- #

CREATE TABLE inventory(
	dealership_id int NOT NULL,
	VIN varchar(250) NOT NULL UNIQUE,
	FOREIGN KEY (dealership_id) REFERENCES dealerships(id),
	FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);

# ---------------------------------------------------------------------- #
# Add table "sales_contracts"                                                 #
# ---------------------------------------------------------------------- #

CREATE TABLE sales_contracts(
	id int AUTO_INCREMENT PRIMARY KEY,
	name varchar(50) NOT NULL,
	phone varchar(50) NOT NULL,
	VIN varchar(50) NOT NULL,
	FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);

# ---------------------------------------------------------------------- #
# Add table "lease_contracts"                                                 #
# ---------------------------------------------------------------------- #

CREATE TABLE lease_contracts(
	id int AUTO_INCREMENT PRIMARY KEY,
	name varchar(50) NOT NULL,
	phone varchar(50) NOT NULL,
	VIN varchar(50) NOT NULL,
	FOREIGN KEY (VIN) REFERENCES vehicles(VIN)
);



/* ---------------------------------------------------- */
/* Sample data                                          */
/* ---------------------------------------------------- */
INSERT INTO dealerships (name,address,phone) VALUES
  ('City Motors'     ,'123 Main St, Charlotte, NC' , '7045550101'),
  ('Lakeside Auto'   ,'88 Lakeview Rd, Raleigh, NC', '9195550134'),
  ('Peak Performance','2 Summit Blvd, Denver, CO'  , '3035550166'),
  ('Desert Wheels'   ,'740 Cactus Ave, Phoenix, AZ', '4805550199'),
  ('Seaside Cars'    ,'51 Ocean Dr, Miami, FL'     , '3055550222');

INSERT INTO vehicles (VIN,make,model,color,sold) VALUES
  ('1FTRW08L0XYZ12345','Ford'   ,'F-150'     ,'Blue'  ,0),
  ('1C6RR7KT2AB456789','RAM'    ,'1500'      ,'White' ,1),
  ('3FA6P0H73DR876543','Ford'   ,'Fusion'    ,'Red'   ,0),
  ('1HGCM82633A654321','Honda'  ,'Accord'    ,'Black' ,1),
  ('1ZVBP8AM8E5123456','Ford'   ,'Mustang'   ,'Red'   ,0);


INSERT INTO inventory (dealership_id, VIN) VALUES
  (1,'1FTRW08L0XYZ12345'),
  (2,'1C6RR7KT2AB456789'),
  (3,'3FA6P0H73DR876543'),
  (1,'1HGCM82633A654321'),
  (5,'1ZVBP8AM8E5123456');

INSERT INTO sales_contracts (name,phone,VIN) VALUES
  ('Alice Baker' ,'7045550404','1C6RR7KT2AB456789'),
  ('Doug Smith'  ,'7045550444','1HGCM82633A654321'),
  ('Eva Johnson','3055550555','1ZVBP8AM8E5123456'),
  ('Frank Lopez','3035550666','1FTRW08L0XYZ12345'),
  ('Grace Lee'  ,'9195550777','3FA6P0H73DR876543');

INSERT INTO lease_contracts (name, phone, VIN) VALUES
  ('Harry Grant','7045550888','1FTRW08L0XYZ12345'),
  ('Ivy Carter' ,'9195550999','1C6RR7KT2AB456789'),
  ('Jack Wang'  ,'3035551111','3FA6P0H73DR876543'),
  ('Kim Patel'  ,'4805551222','1HGCM82633A654321'),
  ('Luna Diaz'  ,'3055551333','1ZVBP8AM8E5123456');
