CREATE DATABASE EcobikeRentalDatabase;

use EcobikeRentalDatabase;
CREATE TABLE Station (
    stationID int,
    stationName varchar(50),
    address varchar(100),
	totalBike int,
	available int,
	PRIMARY KEY(stationID)
);

CREATE TABLE Bike (
    bikeID int,
    type varchar(45),
	description varchar(255),
	price int,
	name varchar(45),
	status varchar(10),
	battery int,
	stationID int,
	PRIMARY KEY(bikeID),
	FOREIGN KEY (stationID) REFERENCES Station(stationID)
);

CREATE TABLE Card (
    cardID int IDENTITY(1,1) PRIMARY KEY,
    cardHolderName varchar(45),
    cardNumber varchar(45),
	transactionDescription varchar(255),
	expirationDate varchar(15),
	securityCode varchar(10),
	issuingBank varchar(50),
);

CREATE TABLE Customer (
    customerID int PRIMARY KEY,
    customerName varchar(50),
    birthday varchar(45),
	gender varchar(6),
	cardNumber varchar(45)
);

CREATE TABLE Rent(
    rentID int IDENTITY(1,1) PRIMARY KEY,
	status varchar(10),
    timeStart time(6),
    timeEnd time(6),
	totalTimeRent int,
	bikeID int,
	customerID int,
	FOREIGN KEY (bikeID) REFERENCES Bike(bikeID),
	FOREIGN KEY (customerID) REFERENCES Customer(customerID)
);

CREATE TABLE Transactions (
    transactionID int IDENTITY(1,1) PRIMARY KEY,
    code varchar(45),
    transactionName varchar(50),
	totalTimeRent int,
	totalMoney int,
	rentID int, 
	FOREIGN KEY (rentID) REFERENCES Rent(rentID)
);

INSERT INTO Station(stationID, stationName, address, totalBike, available)
VALUES (1911, 'HUST', 'so 1 Dai Co Viet', 10, 10),
		(1912, 'CVTHONGNHAT', 'so 1 Tran Nhan Tong', 20, 20),
		(1913, 'NUCE', 'so 55 Giai Phong', 5, 5),
		(1914, 'NEU', 'so 207 Giai Phong', 10, 10);

INSERT INTO Bike(bikeID, type, description, price, name, status, battery, stationID)
VALUES (191101, '1', 'Xe', 10, '', 'available', 90, 1911),
		(191103, '2', 'Xe', 15,	'',  'available', 90, 1911),
		(191102, '1', 'Xe', 10, '', 'available', 90, 1911),
		(191104, '3', 'Xe',20, '', 'available', 90, 1911);

INSERT INTO Card(cardHolderName, cardNumber, transactionDescription, expirationDate, securityCode, issuingBank)
VALUES ('TRAN VAN TRI', '118609_group18_2020', 'TVT', '1125', '390', 'Vietinbank');

INSERT INTO Customer( customerID, customerName, birthday,gender, cardNumber)
VALUES (20173410, 'Tran Van Tri', '060599', 'Nam', '118609_group18_2020');
	

		




