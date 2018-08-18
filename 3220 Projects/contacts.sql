
drop table if exists contacts;
create table contacts(
id  integer auto_increment primary key,
name varchar(255) not null,
phone varchar(255) not null
);

insert into contacts values (1, 'Amy', 1234567890);
insert into contacts values (2, 'Joe', 2345678901);
insert into contacts values (3, 'John', 3456789012);
insert into contacts values (4, 'Tom', 5678901234);

insert into contacts values (5, 'Benny', 5678901234);

create table contacts_fields(
id  integer auto_increment primary key,
contact_id integer references contacts(id),
field varchar(255) not null,
value varchar(255) not null
);

