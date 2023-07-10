CREATE DATABASE msproducts;

DROP TABLE IF EXISTS product;

CREATE TABLE product(
	id bigint auto_increment,
	date date not null,
    description VARCHAR(30) not null,
    name varchar(20) not null,
    imgUrl varchar(50),
    price decimal(10,2) not null,

	CONSTRAINT product_pk PRIMARY KEY (id)
);

DROP TABLE IF EXISTS category;

CREATE TABLE category(
	id int auto_increment,
    name varchar(20) not null unique,
    
    CONSTRAINT category_pk PRIMARY KEY (id, name)
);



