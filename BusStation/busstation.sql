drop user if exists'user1'@'localhost';
CREATE USER 'user1'@'localhost' IDENTIFIED BY 'user1';
GRANT ALL ON busStation.* TO 'user1'@'localhost' IDENTIFIED BY 'user1';

drop database if exists busStation;
create database if not exists busStation;
use busStation;
#create DB tables
CREATE TABLE IF NOT EXISTS employees (
    firstName CHAR(64),
    lastName CHAR(64),
    id INT(2),
    busNum INT(4),
    salary INT(4),
    employeePosition CHAR(20),
    employeePassword CHAR(10),
    PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS vehicles (
    vehicleType CHAR(20),
    busNum INT(4),
    tripType CHAR(20),
    PRIMARY KEY (busNum)
);
CREATE TABLE IF NOT EXISTS customers (
    firstName CHAR(64),
    lastName CHAR(64),
    mobileNum INT(11),
    email char(40),
    userName CHAR(20),
    customerPassword char(10),
    PRIMARY KEY (userName)
);
CREATE TABLE IF NOT EXISTS trip (
	tripCode int(2),
	tripType char(20),
    tripStart CHAR(64),
    tripEnd CHAR(64),
    busNum INT(11),
    startTime INT(2),
    driverId INT(2),
    capacity INT(2),
    price int(3),
    PRIMARY KEY (driverId,busNum,startTime)
);
	#internal trips
	insert into trip values (
	1,'internal','tahrir','6th of october',1001,8,1,25,20);
    insert into trip values (
	2,'internal','6th of october','tahrir',1001,12,1,25,20);
	insert into trip values (
	3,'internal','tahrir','6th of october',1002,13,2,25,20);   
    insert into trip values (
	4,'internal','6th of october','tahrir',1002,17,2,25,20);
    #minibus internal trips
    insert into trip values (
	5,'internal','tahrir','6th of october',1003,17,3,10,20);
    insert into trip values (
	6,'internal','6th of october','tahrir',1003,21,3,10,20);
    insert into trip values (
	7,'internal','tahrir','6th of october',1004,21,4,10,20);
    insert into trip values (
	8,'internal','6th of october','tahrir',1004,1,4,10,20);
    #external trips
    insert into trip values (
	9,'external','cairo','alex',2001,9,11,25,100);
    insert into trip values (
	10,'external','alex','cairo',2001,21,11,25,100);
    insert into trip values (
	11,'external','cairo','alex',2002,21,12,25,100);
    insert into trip values (
	12,'external','alex','cairo',2002,9,12,25,100);
     insert into trip values (
	13,'external','cairo','alex',2005,15,15,10,100);
    insert into trip values (
	14,'external','alex','cairo',2005,3,15,10,100);
    insert into trip values (
	15,'external','cairo','alex',2006,3,16,10,100);
    insert into trip values (
	16,'external','alex','cairo',2006,15,16,10,100);
    
    insert into trip values (
	17,'external','cairo','sharm',2003,1,13,25,250);
    insert into trip values (
	18,'external','sharm','cairo',2003,13,13,25,250);
    
    #employees
insert into employees values  (
  'Ahmed','nada',1,1001,2500,'driver','4ru2y6rppm');
  insert into employees values (
  'Ahmed','hamed',2,1002,2500,'driver','cu8hoyc8x6');
  insert into employees values (
  'mohamed', 'karim',3,1003,2500,'driver','lk94d0opz0');
  insert into employees values (
  'mohamed', 'kazem',4,1004,2500,'driver','h1z50zjmej');
  
  insert  into employees values (
  'Nader', 'Toma',11,2001,2500,'driver','nmovub50yj');
  insert into employees values (
  'Reda', 'Bahar',12,2002,2500,'driver','ktx6cktn13');
  insert into employees values (
  'Mazen', 'Touma',13,2003,2500,'driver','m730ax7sir');
  insert into employees values (
  'Haytham', 'Seif',14,2004,2500,'driver','vddnqhn07j');
  insert into employees values (
  'Abdelrahman', 'Awad',15,2005,2500,'driver','kthx57sk8d');
  insert into employees values (
  'Adel', 'Bishara',16,2006,2500,'driver','obmlypz2jl');
  #2 managers
   insert into employees values (
  'Nour', 'Sabbagh',50,null,6000,'manager',"505050");
  insert into employees values (
  'Hany', 'Antar',60,null,6000,'manager',"606060");
  
  #vehicles
  insert  into vehicles values(
  'bus',1001,'internal');
  insert  into vehicles values(
  'bus',1002,'internal');
   insert  into vehicles values(
  'minibus',1003,'internal');
   insert  into vehicles values(
  'minibus',1004,'internal');
  insert  into vehicles values(
  'bus',2001,'external');
  insert  into vehicles values(
  'bus',2002,'external');
  insert  into vehicles values(
  'bus',2003,'external');
  insert  into vehicles values(
  'bus',2004,'external');
  insert  into vehicles values(
  'minibus',2005,'external');
  insert  into vehicles values(
  'minibus',2006,'external');
 
  #insertCustomer
insert  into customers values (
  'youssef','kishk',01066713878,'ykishk@hotmail.com','youssef_kishk','123');
  