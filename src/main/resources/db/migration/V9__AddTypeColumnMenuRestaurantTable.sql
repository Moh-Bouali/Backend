ALTER TABLE menu_restaurant
    ADD COLUMN type varchar(50) DEFAULT NULL
    AFTER price;