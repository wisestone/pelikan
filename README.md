# pelikan

## Setup MariaDB or MySql

```sql
-- create database with collate utf8_general_ci
CREATE DATABASE `pelikandb`;

-- pelikan / !pelican#12
CREATE USER 'pelikan'@'localhost' IDENTIFIED BY '!pelican#12';
GRANT USAGE ON *.* TO 'pelikan'@'localhost';
GRANT SELECT, EXECUTE, SHOW VIEW, ALTER, ALTER ROUTINE, CREATE, CREATE ROUTINE, CREATE TEMPORARY TABLES, CREATE VIEW, DELETE, DROP, EVENT, INDEX, INSERT, REFERENCES, TRIGGER, UPDATE, LOCK TABLES  ON `pelikandb`.* TO 'pelikan'@'localhost';
FLUSH PRIVILEGES;
```