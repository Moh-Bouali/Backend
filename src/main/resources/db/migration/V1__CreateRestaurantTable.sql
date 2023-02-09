CREATE TABLE restaurant
(
    id   int     NOT NULL AUTO_INCREMENT,
    name varchar(50),
    address varchar(50),
    owner varchar(50),
    PRIMARY KEY (id),
    UNIQUE (name)
);