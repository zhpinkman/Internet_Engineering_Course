use test1;

show tables;

drop table if exists USERS;

create table USERS (
   email varchar(255) primary key,
   firstName tinytext not null,
   lastName tinytext not null,
   credit double not null default 0,
   phoneNumber tinytext not null,
   locationX double not null default 0,
   locationY double not null default 0
#    cartId tinytext
);

describe USERS;

drop table if exists RESTAURANTS;

create table RESTAURANTS (
    id varchar(255) primary key,
    name tinytext not null,
    description text,
    locationX double not null default 0,
    locationY double not null default 0,
    logo text not null
);

describe RESTAURANTS;