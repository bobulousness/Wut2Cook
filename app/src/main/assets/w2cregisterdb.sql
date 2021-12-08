create database wut2cookusers;
use wut2cookusers;
create table register (
	Name varchar(255) not null,
    password varchar(255) not null,
    gender enum ('male','female','other'),
    email varchar(255) not null
);

insert into register values('John Doe', 'cooky@123', 'male', 'johndoe@gmail.com');

select *
from register;

