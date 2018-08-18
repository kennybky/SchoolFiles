drop table if exists librarybooks;

create table librarybooks (
    id      integer auto_increment primary key,
    title    varchar(255) not null,
    isbn varchar(255) not null,
    author  varchar(255) not null,
    year year,
    available boolean
);

insert into librarybooks (title, isbn, author, year, available)
values ("Waterfalls", 12345678, "TLC", "1999", true );

insert into librarybooks (title, isbn, author, year, available)
values ("Haven", 9876672, "John Knick", "2000", true );

insert into librarybooks (title, isbn, author, year, available)
values ("The Night Tower", 72346378, "Stephanie Myers", "2010", true);