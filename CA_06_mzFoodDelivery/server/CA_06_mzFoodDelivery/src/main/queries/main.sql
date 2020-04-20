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
);


describe USERS;


drop table if exists RESTAURANTS;

create table RESTAURANTS (
    id varchar(255) primary key,
    name tinytext not null,
    locationX double not null default 0,
    locationY double not null default 0,
    logo text not null
);

describe RESTAURANTS;

drop table if exists FOODS;


create table FOODS (
  name varchar(255),
  restaurantId varchar(255) references RESTAURANTS(id),
  description text not null,
  popularity double not null,
  price double not null,
  image text not null,
  count int default -1,
  newPrice double default -1,
  primary key(name, restaurantId)
);

describe FOODS;

drop table if exists USERCART;

create table USERCART (
    userEmail varchar(255) primary key references USERS(email),
    restaurantId tinytext not null references RESTAURANTS(id),
    foodName tinytext not null,
    quantity int not null default 1
);


describe USERCART;

drop table if exists DELIVERIES;

create table DELIVERIES (
    id varchar(255) primary key,
    velocity double not null,
    locationX double not null,
    locationY double not null
);

describe DELIVERIES;


drop table if exists ORDERS;

create table ORDERS (
    orderId int not null,
    status tinytext not null,
    deliveryId varchar(255) references DELIVERIES(id),
    startingDeliveryTime DATE,
    userEmail varchar(255) not null references USERS(email),
    primary key (userEmail, orderId)
);


describe ORDERS;


