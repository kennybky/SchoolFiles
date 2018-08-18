drop table if exists courses;
drop table if exists mappings;

create table courses(
id integer auto_increment primary key,
course varchar(255) unique,
is_quarter boolean
);

insert into courses values(1, 'CS122', true);
insert into courses values(2, 'CS201', true);
insert into courses values(3, 'CS202', true);
insert into courses values(4, 'CS203', true);
insert into courses values(5, 'CS2011', false);
insert into courses values(6, 'CS2012', false);
insert into courses values(7, 'CS2013', false);

create table mappings(
id integer auto_increment primary key,
quarter varchar(255) references courses(course),
semester varchar(255) references courses(course)
);

insert into mappings values (1, 'CS201', 'CS2011'); 
insert into mappings values (2, 'CS202', 'CS2012'); 
