drop table Hospital cascade constraints;
drop table Users cascade constraints;
drop table Vaccine cascade constraints;

create table Hospital (
	name 		varchar2(255) primary key,  
	location 	varchar2(255) not null, 
	pfizer 		number(10,0) not null,
	moderna 	number(10,0) not null, 
	az 			number(10,0) not null
);

create table Users (
	id_num 			number(19,0) primary key, 
	name 			varchar2(255) not null, 
	age 			number(10,0) not null, 
	address 		varchar2(255) not null, 
	date1 			varchar2(255) not null, 
	date2 			varchar2(255) not null,
	vaccine_name 	varchar2(255) not null,
	hospital_name 	varchar2(255) not null
);

create table Vaccine (
	name 		varchar2(255) primary key, 
	target_age 	number(10,0) not null, 
	period 		number(10,0) not null, 
	platform 	varchar2(255) not null, 
	temperature varchar2(255) not null,
	storage 	number(10,0) not null
);

alter table Users add constraint Hospital foreign key (hospital_name) references Hospital;
alter table Users add constraint Vaccine foreign key (vaccine_name) references Vaccine;
