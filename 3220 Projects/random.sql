drop table if exists reservations;
create table reservations(
id integer auto_increment primary key,
time varchar(255),
mon varchar(255),
tue varchar(255),
wed varchar(255),
thr varchar(255),
fri varchar(255)
);

insert into reservations values (1, '08:00 - 09:00', null, null, null, null ,null);
insert into reservations values (2, '09:00 - 10:00', null, 'Kang', null, null ,null);
insert into reservations values (3, '10:00 - 11:00', 'Pamula', null, null, null ,null);
insert into reservations values (4, '11:00 - 12:00', null, null, null, null ,null);
insert into reservations values (5, '12:00 - 13:00', null, null, null, null ,null);
insert into reservations values (6, '13:00 - 14:00', null, null, 'Abbot', null ,null);
insert into reservations values (7, '14:00 - 15:00', null, null, 'Abbot', 'Sun' ,null);
insert into reservations values (8, '15:00 - 16:00', null, null, null, 'Sun' ,null);
insert into reservations values (9, '16:00 - 17:00', null, null, null, null ,null);
