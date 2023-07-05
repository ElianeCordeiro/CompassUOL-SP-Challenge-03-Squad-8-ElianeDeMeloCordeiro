DROP TABLE IF EXISTS product;

CREATE TABLE product(
	id int auto_increment,
	date date not null,
    description VARCHAR(30) not null,
    name varchar(20) not null unique,
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

ALTER TABLE product 
	ADD COLUMN category varchar(20) not null,
    ADD CONSTRAINT product_category_fk FOREIGN KEY (category)
			REFERENCES CATEGORY (name);



