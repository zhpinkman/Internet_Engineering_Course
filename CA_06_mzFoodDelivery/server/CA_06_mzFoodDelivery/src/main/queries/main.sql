use mzFoodDelivery;

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
  restaurantId varchar(255),
  description text not null,
  popularity double not null,
  price double not null,
  image text not null,
  count int default -1,
  newPrice double default -1,
  primary key(name, restaurantId),
  foreign key (restaurantId) references RESTAURANTS(id)
);

describe FOODS;

drop table if exists USERCART;

create table USERCART (
    userEmail varchar(255) references USERS(email),
    restaurantId varchar(255),
    foodName varchar(255) not null,
    quantity int not null default 1,
    foreign key (foodName, restaurantId) references FOODS(name, restaurantId),
    primary key (userEmail, restaurantId, foodName)
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
    userEmail varchar(255) not null,
    orderId int not null,
    status varchar(255) not null default 'SEARCHING',
    deliveryId varchar(255) not null,
    startingDeliveryTime DATE,
    primary key (userEmail, orderId)
);

describe ORDERS;

select * from USERS;

show TABLES ;

show tables;

create table ORDERITEMS (
    userEmail varchar(255),
    id int,
    restaurantId varchar(255),
    foodName varchar(255),
    quantity int not null,
    primary key (userEmail, id, restaurantId, foodName)
);


select * from ORDERITEMS;

delete from ORDERITEMS where userEmail = 'ekhamespanah@yahoo.com' and orderId = 0 and restaurantId = '5e4fcf14af68ed25d5900e9f' and foodName = 'الفردو با مرغ ';


select * from ORDERS;

delete from ORDERS where userEmail = 'ekhamespanah@yahoo.com' and orderId = 0;

select count(*) from DELIVERY;


select * from ORDERS;

select * from USERS;

select * from ORDERS where userEmail = 'ekhamespanah@yahoo.com' and orderId = 1;