drop table if exists guestbook;

create table guestbook (
    id      integer auto_increment primary key,
    name    varchar(255),
    message varchar(8000),
    date    timestamp
);

insert into guestbook (name, message, date)
    values ('john', 'hello', '2017-04-08 17:10:00');
insert into guestbook (name, message, date) values ('joe', 'hi', now());

