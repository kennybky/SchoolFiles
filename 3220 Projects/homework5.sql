drop table if exists files;
drop table if exists users;

create table users (
    id          integer auto_increment primary key,
    username        varchar(255) unique not null,
    password varchar(255) not null,
    fname varchar(255) not null,
    lname varchar(255)
);

insert into users values (1, 'cysun', 'abcd', 'Chengyu', 'Sun');
insert into users values (2, 'kktogs', 'xyz', 'Adekola', 'Togunloju');

create table files (
    id          integer auto_increment primary key,
    name        varchar(255),
	parent_id   integer references files(id),
	is_folder   boolean default true,
    type        varchar(255),
    date        timestamp not null,
    size        long,
    user_id      integer references users(id)
);

insert into files values (1, 'MyFiles', null, true, 'Folder', now(), null, 1);
insert into files values (2, 'Documents', null, true, 'Folder', now(), null, 1);
insert into files values (3, 'Temp', null, true, 'Folder', now(), null, 1);
insert into files values (4, 'Other', 1, true, 'Folder', now(), null, 1);
insert into files values (5, 'MyDocuments', null, true, 'Folder', now(), null, 2);
insert into files values (6, 'Projects', 5, true, 'Folder', now(), null, 2);
insert into files values (7, 'Courses', 5, true, 'Folder', now(), null, 2);
insert into files values (8, 'Assignments', 5, true, 'Folder', now(), null, 2);


