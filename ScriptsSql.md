# paywebapp

create table prod.user
(
    ID        int auto_increment,
    EMAIL     varchar(60) not null,
    PASSWORD  varchar(64) not null,
    FIRSTNAME varchar(60) not null,
    LASTNAME  varchar(60) not null,
    BALANCE   float       null,
    primary key (ID, EMAIL),
    constraint ix_auth_email
        unique (EMAIL, PASSWORD)
);

create table prod.bankaccount
(
    ID      int auto_increment
        primary key,
    USER_ID int          not null,
    NAME    varchar(60)  not null,
    IBAN    varchar(100) null,
    constraint bankaccount_ibfk_1
        foreign key (USER_ID) references prod.user (ID)
);

create index ID_USER
    on prod.bankaccount (USER_ID);

create table prod.banktransaction
(
    ID             int auto_increment
        primary key,
    USER_ID        int         not null,
    BANKACCOUNT_ID int         not null,
    AMOUNT         float       null,
    INTIME         datetime    not null,
    OPERATION_TYPE varchar(60) null,
    constraint banktransaction_ibfk_1
        foreign key (USER_ID) references prod.user (ID),
    constraint banktransaction_ibfk_2
        foreign key (BANKACCOUNT_ID) references prod.bankaccount (ID)
);

create index FKgdevdncfjxu6b3yxfgmov6lwl
    on prod.banktransaction (USER_ID);

create index ID_BANKACCOUNT
    on prod.banktransaction (BANKACCOUNT_ID);

create table prod.transaction
(
    NUMBER         int auto_increment
        primary key,
    USER_ID        int          not null,
    USER_DEBTOR_ID int          not null,
    AMOUNT         float        null,
    INTIME         datetime     not null,
    DESCRIPTION    varchar(200) not null,
    FEE            float        null,
    constraint FK397bx4xdsk3do9inb8ix5134k
        foreign key (USER_DEBTOR_ID) references prod.user (ID),
    constraint FKsg7jp0aj6qipr50856wf6vbw1
        foreign key (USER_ID) references prod.user (ID)
);

create index ID
    on prod.transaction (USER_ID, USER_DEBTOR_ID);

create table prod.user_contact_user_list
(
    user_id              int not null,
    contact_user_list_id int not null,
    constraint FKluul10ab43n9n9e73f964tfp2
        foreign key (user_id) references prod.user (ID),
    constraint FKsucc8fa5tl4jwrfw36xj4lh
        foreign key (contact_user_list_id) references prod.user (ID)
);

create index UK_af1nlkdbo74mshvhtxyfqwy24
    on prod.user_contact_user_list (contact_user_list_id);

