drop table if exists departments;
drop table if exists faculty;

create table departments (
    id          integer auto_increment primary key,
	name varchar(255),
	size  integer,
    chair boolean
);

insert into departments values (1, 'Computer Science', 2, true);
insert into departments values (2, 'Electrical Engineering', 1, false);

create table faculty (
    id          integer auto_increment primary key,
    name        varchar(255),
    chair  boolean,
    dept  integer references departments(id)
);

insert into faculty values (1, 'Pamula', true, 1);
insert into faculty values (2, 'Sun', false, 1);
insert into faculty values (3, 'Argawal', false, 2);



