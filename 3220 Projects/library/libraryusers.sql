drop table if exists libraryusers;

create table libraryusers (
    id      integer auto_increment primary key,
    username    varchar(255) not null,
    password varchar(25) not null,
    type varchar(25) not null,
    fname varchar(255),
    lname varchar(255)
);

insert into libraryusers (username, password, type, fname, lname)
values ("cysun", "abcd", "admin", "Chengyu", "Sun");

insert into libraryusers (username, password, type, fname, lname)
values ("kktogs", "xyz", "student", "Adekola", "Togunloju");

insert into libraryusers (username, password, type, fname, lname)
values ("kenny", "cs3220", "student", "Kenny", "Michaels");
