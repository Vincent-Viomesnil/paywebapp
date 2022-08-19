/* Setting up PROD DB */
create database prod;
use prod;

create table user(
     ID int AUTO_INCREMENT,
     EMAIL varchar(60) NOT NULL,
     PASSWORD varchar(60) NOT NULL,
     FIRSTNAME varchar(60) NOT NULL,
     LASTNAME varchar(60) NOT NULL,
     DESCRIPTION varchar(200) NOT NULL,
     BALANCE float,
     PRIMARY KEY(ID,EMAIL)
);

create table transaction(
    NUMBER int AUTO_INCREMENT,
    ID int NOT NULL,
    EMAIL varchar(60) NOT NULL,
    AMOUNT float,
    INTIME DATETIME NOT NULL,
    DESCRIPTION varchar(200) NOT NULL,
    PRIMARY KEY(NUMBER),
    FOREIGN KEY(ID, EMAIL) REFERENCES user(ID, EMAIL)
);

commit;

/* Setting up TEST DB */
create database test;
use test;

create table user(
     ID int AUTO_INCREMENT,
     EMAIL varchar(60) NOT NULL,
     PASSWORD varchar(60) NOT NULL,
     FIRSTNAME varchar(60) NOT NULL,
     LASTNAME varchar(60) NOT NULL,
     DESCRIPTION varchar(200) NOT NULL,
     BALANCE float,
     PRIMARY KEY(ID,EMAIL)
);


create table transaction(
    NUMBER int AUTO_INCREMENT,
    ID int NOT NULL,
    EMAIL varchar(60) NOT NULL,
    AMOUNT float,
    INTIME DATETIME NOT NULL,
    DESCRIPTION varchar(200) NOT NULL,
    PRIMARY KEY(NUMBER),
    FOREIGN KEY(ID, EMAIL) REFERENCES user(ID, EMAIL)
);

commit;