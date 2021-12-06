drop database if exists wut2cookrecipes;
create database Wut2CookRecipes;
use Wut2CookRecipes;
create table recipe (
	Name varchar(255) not null,
    Rating varchar(255) not null,
    EaseofPrep varchar(255) not null,
    Notes varchar(255) not null,
    TypeOfMeal varchar(255) not null,
    PrepTime varchar(255) not null,
    Photo varchar(255) not null,
    Cookbook varchar(255) not null,
    PageNum varchar(255) not null,
    Ingredients varchar(255) not null,
    Slowcooker varchar(255) not null,
    Link varchar(255) not null,
    DateMade varchar(255) not null,
    Make varchar(255) not null,
    primary key(Name)
);
/*
Database file comes from 
*/
load data infile 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Recipes-All Recipes (1).csv'
into table recipe
FIELDS TERMINATED BY ',' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(Name, Rating, EaseofPrep, Notes, TypeOfMeal, PrepTime, Photo, Cookbook, PageNum, Ingredients, Slowcooker, Link, DateMade, Make);

select * 
from recipe;