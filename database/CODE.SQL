CREATE DATABASE BOOKING;
USE BOOKING;
CREATE TABLE USERS
(
	user_id BIGINT auto_increment not null primary key,
    username VARCHAR(255) not null,
    password varchar(255)not null,
    email varchar(255) not null,
    phone_number char(15) null default '',
    status int default 0
);
CREATE TABLE MEMBERS
(
	member_id BIGINT auto_increment not null primary key,
    user_id BIGINT ,
    username varchar(255) not null,
    password varchar(255)not null,
    email varchar(255) not null,
    phone_number char(15) null default '',
    address text default '',
    gender text ,
    created timestamp default current_timestamp(),
    creator text,
    modified timestamp default null,
    modifier text,
    FOREIGN KEY (user_id) references Users(user_id)
    );
CREATE TABLE TOKENS
(
	token_id BIGINT not null primary key auto_increment,
    member_id BIGINT UNIQUE,
    user_id	 BIGINT UNIQUE,
    code VARCHAR(255) NOT NULL,
    effect_from timestamp,
    effect_until timestamp,
    status int default 0,
    FOREIGN KEY (MEMBER_ID) REFERENCES MEMBERS(MEMBER_ID),
    foreign key(USER_ID) references USERS(USER_ID)
    );
CREATE TABLE ROLES
(
	ROLE_ID BIGINT not null primary key auto_increment,
    NAME TEXT,
    EFFECT_FROM timestamp,
    EFFECT_UNTIL timestamp,
    STATUS INT DEFAULT 0 
);
CREATE TABLE ROLE_MEMBERS
(
	role_member_id BIGINT	not null primary key auto_increment,
    role_id BIGINT,
    member_id BIGINT,
    created timestamp default current_timestamp(),
    status int default 0,
    foreign key(role_id) references roles(role_id),
    foreign key(member_id) references members(member_id)
);
    
	