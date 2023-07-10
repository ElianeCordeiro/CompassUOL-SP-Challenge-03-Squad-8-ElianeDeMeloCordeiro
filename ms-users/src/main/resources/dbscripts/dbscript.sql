-- create database msusers;

drop table if exists user;

create table user(
	id bigint auto_increment,
    firstName varchar(20) not null,
    lastName varchar(30) not null,
    email varchar(20) not null,
    password varchar(20) not null,
    
    constraint user_pk primary key (id)
);

drop table if exists role;

create table role(
	id bigint auto_increment,
    name varchar(20),
    
    constraint role_pk primary key (id)
)

