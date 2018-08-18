drop table if exists files;

create table files (
    id          integer auto_increment primary key,
    name        varchar(255),
    parent_id   integer references files(id),
    is_folder   boolean default 0
);

insert into files values (1, 'Documents', null, 1);
insert into files values (2, 'Users', null, 1);
insert into files values (3, 'John', 2, 1);
insert into files values (4, 'Jane', 2, 1);
insert into files values (5, 'Courses', 3, 1);
insert into files values (6, 'CS3220', 5, 1);
insert into files values (7, 'CS5220', 5, 1);

insert into files values (8, 'log.txt', null, 0);
insert into files values (9, 'contacts.xls', 3, 0);
insert into files values (10, 'File.java', 6, 0);
insert into files values (11, 'files.sql', 6, 0);
insert into files values (12, 'Homework1.pdf', 7, 0);
