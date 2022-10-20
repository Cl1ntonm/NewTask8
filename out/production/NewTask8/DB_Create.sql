CREATE DATABASE IF not exists PoisePMS;
show databases;
use PoisePMS;

DROP TABLE IF exists Projects;
CREATE TABLE Projects(
	project_number int NOT NULL AUTO_INCREMENT,
	name varchar(50),
	type varchar(50),
	address varchar(50),
	ERF varchar(50),
	amount_charged float,
	amount_paid float,
	deadline_date date,
	completed_date date,
	finalised varchar (3),
	primary key (project_number)
);
describe Projects;

DROP TABLE IF exists Persons;
CREATE TABLE Persons(
	id int NOT NULL AUTO_INCREMENT,
	project_number int,
	first_name varchar(50),
	last_name varchar (50),
	telephone varchar(50),
	email varchar(50),
	address varchar(50),
	role_id int,
	primary key (id)
);
describe Persons;

DROP TABLE IF exists Roles;
CREATE TABLE Roles(
	id int NOT NULL AUTO_INCREMENT,
	role varchar(50),
	primary key (id)
);
describe Roles;

INSERT INTO Projects VALUES(1,'Luke House','House','45 Ach to Island','456',500,400,'2020-01-01','2020-01-01','no');
INSERT INTO Projects VALUES(2,'Harry House','House','2 Grimmauld Place','456',400,300,'2020-01-01','2020-01-01','no');
INSERT INTO Projects VALUES(3,'Studio Apartment','Studio Apartment','1544 North Saint Andrews tree','457',200,200,'2019-01-01','2022-01-02','yes');
INSERT INTO Projects VALUES(4,'Cameron Garage','Garage','43 California close','459',600,200,'2018-01-01','2022-01-01','yes');
INSERT INTO Projects VALUES(5,'NightClub', 'NightClub', '43 California close', '459', 200, 200, '2018/01/01', '2021/01/01', 'no');
INSERT INTO Projects VALUES(6,'Jack Flat','Flat','2912 4th Str Santa Monica','461',200,200,'2025-01-01','2025-01-01','no');

INSERT INTO Persons VALUES(1,1,'Luke','SkyWalker','727265101','Luke@email.com','15 Tatooine',1);
INSERT INTO Persons VALUES(2,1,'Leia','SkyWalker','827238752','leia@email.com','1 Alderaan rd',2);
INSERT INTO Persons VALUES(3,1,'Han','Solo','738928751','han@email.com','76 Correllia',3);
INSERT INTO Persons VALUES(4,1,'C','3po','972371680','3cpo@email.com','16 Tatooine',4);
INSERT INTO Persons VALUES(5,1,'R2','D2','751278365','r2d2@email.com','67 Naboo',5);
INSERT INTO Persons VALUES(6,2,'Harry','Potter','837877083','harry@email.com','89 Understair Land',1);
INSERT INTO Persons VALUES(7,2,'Hermione','Granger','873476873','Hermione@email.com','8 Heathgate Hampstead',2);
INSERT INTO Persons VALUES(8,2,'Ron','Weasley','738928751','ron@email.com','12 Burrow Place',3);
INSERT INTO Persons VALUES(9,2,'Rubeus','Hagrid','762635478','rubeus@email.com','34 West Country',4);
INSERT INTO Persons VALUES(10,2,'Severus','Snape','275619779','severus@email.com','1960 Spinners End',5);
INSERT INTO Persons VALUES(11,3,'Alvin','Seville','871872650','alvin@email.com','1544 North SaintAndrews smallroom',1);
INSERT INTO Persons VALUES(12,3,'Theodore','Seville','729787297','theodore@email.com','1544 North Saint Andrews small room',2);
INSERT INTO Persons VALUES(13,3,'Simon','Seville','738977521','simon@email.com','1544 North Saint Andrews large room',3);
INSERT INTO Persons VALUES(14,3,'Brittany','Miller','874612870','brittany@email.com','78 tree house lane',4);
INSERT INTO Persons VALUES(15,3,'Jeanette','Miller','286091823','jeanette@email.com','78 tree house lane',5);
INSERT INTO Persons VALUES(16,4,'Cameron','Diaz','286091823','cameron@email.com','43 California close',1);
INSERT INTO Persons VALUES(17,4,'Drew','Barrymore','722347299','drew@email.com','78 Malibu crescent',2);
INSERT INTO Persons VALUES(18,4,'Lucy','Liu','738998121','lucy@email.com','90 San Fransisco place',3);
INSERT INTO Persons VALUES(19,4,'John','Bosley','812761095','bosley@email.com','7121 Lonzo Street',4);
INSERT INTO Persons VALUES(20,4,'Charles','Townsend','861872397','charlie@email.com','7121 Lonzo Street',5);
INSERT INTO Persons VALUES(21,5,'Buffy','Summers','871287562','buffy@email.com','1630 Revello Drive',1);
INSERT INTO Persons VALUES(22,5,'Xander','Harris','162954879','xander@eail.com','231 Sunnydale Court',2);
INSERT INTO Persons VALUES(23,5,'Willow','Rosenberg','348712543','willow@email.com','56 Tree Lane',3);
INSERT INTO Persons VALUES(24,5,'Cordelia','chase','871263810','cordelia@email.com','23 california close',4);
INSERT INTO Persons VALUES(25,5,'Dawn','Summers','912837459','dawn@email.com','1630 Revello Drive',5);
INSERT INTO Persons VALUES(26,6,'Jack','Tripper','672165031','jack@email.com','L2 Hacienda Palms',1);
INSERT INTO Persons VALUES(27,6,'Janet','Wood','459287103','janet@email.com','R2 4th Str Santa Monica',2);
INSERT INTO Persons VALUES(28,6,'Chrissy','Snow','281673048','chrissy@email.com','R2 4th Str Santa Monica',3);
INSERT INTO Persons VALUES(29,6,'Stanley','Rooper','827301938','stanley@email.com',' 5154 Fountain Avenue',4);
INSERT INTO Persons VALUES(30,6,'Helen','Rooper','819287399','helen@email.com','  5154 Fountain Avenue',5);

INSERT INTO Roles VALUES(1,"customer");
INSERT INTO Roles VALUES(2,"architect");
INSERT INTO Roles VALUES(3,"contractor");
INSERT INTO Roles VALUES(4,"structural_engineer");
INSERT INTO Roles VALUES(5,"Projects_manager");