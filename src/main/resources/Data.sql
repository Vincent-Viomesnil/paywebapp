/* Setting up PROD DB */
create database prod;
use prod;

create table user(
    ID int PRIMARY KEY AUTO_INCREMENT,
    EMAIL varchar(60) PRIMARY KEY,
    PASSWORD varchar(60) NOT NULL,
    FIRSTNAME varchar(60) NOT NULL,
    LASTNAME varchar(60) NOT NULL,
    DESCRIPTION varchar(200) NOT NULL,
    BALANCE float,
);

create table transaction(
    ID int PRIMARY KEY AUTO_INCREMENT,
    EMAIL varchar NOT NULL,
    AMOUNT float,
    INTIME LOCALDATETIME NOT NULL,
    DESCRIPTION varchar(200) NOT NULL,
    FOREIGN KEY (EMAIL)
    REFERENCES user(EMAIL));
)

commit;

/* Setting up TEST DB */
create database test;
use test;

create table user(
     ID int PRIMARY KEY AUTO_INCREMENT,
     EMAIL varchar(60) PRIMARY KEY,
     PASSWORD varchar(60) NOT NULL,
     FIRSTNAME varchar(60) NOT NULL,
     LASTNAME varchar(60) NOT NULL,
     DESCRIPTION varchar(200) NOT NULL,
     BALANCE float,
);


create table transaction(
    ID int PRIMARY KEY AUTO_INCREMENT,
    EMAIL varchar NOT NULL,
    AMOUNT float,
    INTIME LOCALDATETIME NOT NULL,
    DESCRIPTION varchar(200) NOT NULL,
    FOREIGN KEY (EMAIL)
    REFERENCES user(EMAIL));
)

commit;