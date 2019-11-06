DELIMITER $$
CREATE PROCEDURE myproc()
BEGIN
    DECLARE i int default 1;
    WHILE i <= 10000 DO
       SET i = i + 1;
        INSERT INTO employee VALUES (i, 'Shiva', 'Developer');

    END WHILE;
END;