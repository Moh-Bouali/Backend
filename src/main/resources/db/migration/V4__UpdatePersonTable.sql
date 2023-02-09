ALTER TABLE person
    ADD COLUMN role varchar(200) DEFAULT NULL
    AFTER password;