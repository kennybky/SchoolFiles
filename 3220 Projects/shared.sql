drop table if exists shared;

create table shared (
id integer auto_increment primary key,
file_id integer not null references files(id),
user_id integer not null references users(id),
date        timestamp not null
);


