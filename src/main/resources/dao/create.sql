SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS department (
id int PRIMARY KEY auto_increment,
name VARCHAR,
description VARCHAR,
totalemployees int
);

CREATE TABLE IF NOT EXISTS news (
id int PRIMARY KEY auto_increment,
topic VARCHAR,
description VARCHAR,
departmentid int
);

CREATE TABLE IF NOT EXISTS users (
id int PRIMARY KEY auto_increment,
username VARCHAR,
departmentid int,
role VARCHAR
);

CREATE TABLE IF NOT EXISTS departments_news (
id int PRIMARY KEY auto_increment,
newsid INTEGER,
departmentid INTEGER
);