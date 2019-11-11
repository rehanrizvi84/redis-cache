----
DROP PROCEDURE IF EXISTS LOADEMP;
DELIMITER $$
CREATE PROCEDURE LOADEMP()
BEGIN
    DECLARE i int default 1;
    WHILE i <= 10000 DO
       SET i = i + 1;
        INSERT INTO employee VALUES (i, 'Shiva', 'Developer');

    END WHILE;
END;$$

----
DROP PROCEDURE IF EXISTS LOADADDRESS;
DELIMITER $$
CREATE PROCEDURE LOADADDRESS()
BEGIN
    DECLARE i int default 1;
    WHILE i <= 20 DO
       SET i = i + 1;
        INSERT INTO address(addr_id,area,county,state,street) VALUES (i, '132 Avenue', 'CA-1','CA','Street');

    END WHILE;
END;$$

----
DROP PROCEDURE IF EXISTS LOADLOCATION;
DELIMITER $$
CREATE PROCEDURE LOADLOCATION()
BEGIN
    DECLARE i int default 1;
    WHILE i <= 20 DO
       SET i = i + 1;
        INSERT INTO location(LOCATION_ID,LOCATION_NAME,LOCATION_TYPE) VALUES (i, 'Sorting Facility', 'FCLTY');

    END WHILE;
END;$$

----
DROP PROCEDURE IF EXISTS LOADSERVICEDAYS;
DELIMITER $$
CREATE PROCEDURE LOADSERVICEDAYS()
BEGIN
    DECLARE i int default 1;
    WHILE i <= 20 DO
       SET i = i + 1;
        INSERT INTO service_day(ID,COUNTRY_CODE,DATE, TYPE) VALUES (i, '840' ,'2019-07-01', 'W');

    END WHILE;
END;$$

-----
DROP PROCEDURE IF EXISTS LOADHOLIDAY;
DELIMITER $$
CREATE PROCEDURE LOADHOLIDAY()
BEGIN
    DECLARE i int default 1;
    WHILE i <= 20 DO
       SET i = i + 1;
        INSERT INTO holiday(ID,COUNTRY_CODE,HOLIDAY_DATE, TYPE) VALUES (i, '840' ,'2019-07-04', 'C');

    END WHILE;
END;$$

