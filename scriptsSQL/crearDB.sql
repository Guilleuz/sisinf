CREATE DATABASE sisinf_grupo_x;
CREATE SCHEMA SisInf;

SET search_path TO SisInf;

CREATE EXTENSION IF NOT EXISTS postgis WITH SCHEMA SisInf;
drop table if exists system_properties; 

CREATE TABLE Usuario ( 
    name varchar(40) PRIMARY KEY, 
    password varchar(20)
);


CREATE TABLE BiziStation ( 
    id int PRIMARY KEY, /* int */
    capacity int NOT NULL, 
    available int,
    direction varchar(200) NOT NULL,
    lat real NOT NULL,
    long real NOT NULL
);


CREATE TABLE TramStation ( 
    id int PRIMARY KEY, /* int */
    name varchar(200) NOT NULL, 
    way varchar(200) NOT NULL,
    orden int not null,
    direction varchar(200) NOT NULL,
    lat real NOT NULL,
    long real NOT NULL
);

CREATE TABLE BusStation ( 
    id int PRIMARY KEY, /* int */
    direction varchar(200) NOT NULL,
    lat real NOT NULL,
    long real NOT NULL
);

CREATE TABLE BusLine ( 
    id int PRIMARY KEY, /* int */
    name varchar(200) NOT NULL, 
    way varchar(200) NOT NULL
);

CREATE TABLE BusRoute ( 
    busStop int, /* int */
    line int, /* int */
    orden int NOT NULL,
    PRIMARY KEY(busStop, line),
    CONSTRAINT busStop_fk FOREIGN KEY (busStop) REFERENCES BusStation(id),
    CONSTRAINT line_fk FOREIGN KEY (line) REFERENCES BusLine(id)
);

