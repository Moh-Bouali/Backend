ALTER TABLE restaurant
    ADD COLUMN URL varchar(200) DEFAULT NULL
    AFTER owner;