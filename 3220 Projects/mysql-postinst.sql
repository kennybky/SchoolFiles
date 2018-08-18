use mysql;

-- 1. create a new database cs3220stu31

create database cs3220stu31;

-- 2. create a user cs3220stu31, with password abcd, and grant the user
--    all privileges on the database cs3220stu31. 

grant all privileges on cs3220stu31.* to 'cs3220stu31'@'localhost'
	identified by 'abcd' with grant option;

flush privileges;

-- 3. create a table items with two records.

use cs3220stu31;

create table items (
	name		varchar(32),
	price		decimal(8,2),
	quantity	int
);

insert into items values ('milk', 3.89, 2);
insert into items values ('beer', 6.99, 1);

