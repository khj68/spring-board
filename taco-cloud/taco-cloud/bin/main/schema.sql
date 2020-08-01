drop table if exists users;
drop table if exists authorities;
drop table if exists ix_auth_username;

create table if not exists users(
    username varchar2(50) not NULL PRIMARY KEY,
    password varchar2(50) not NULL,
    enabled char(1) default '1'
);

create table if not exists authorities(
    username VARCHAR2(50) NOT NULL,
    authority varchar2(50) NOT NULL,
    constraint fk_authorities_users
        foreign key(username) REFERENCES users(username)
);

create unique index ix_auth_username
    on authorities (username, authority);