ALTER TABLE menu_restaurant
    ADD COLUMN price varchar(50) DEFAULT NULL
    AFTER name;