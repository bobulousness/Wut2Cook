drop database if exists wut2cookusers;
create database wut2cookusers;
use wut2cookusers;
create table register (
	Name varchar(255) not null,
	email varchar(255) not null,
	password varchar(255) not null,
    	gender enum ('male','female','other')
);

create table filters (
    name varchar(255) not null,
);

create table register_filter(
    name varchar(255) not null,
    filter varchar(255) not null,

);

insert into register values('John Doe', 'johndoe@gmail.com', 'cooky@123', 'male');

select *
from register;



