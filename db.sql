CREATE TABLE user (
  id INT AUTO_INCREMENT,
  username VARCHAR(32) DEFAULT NULL,
  password VARCHAR(32) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO user (username, password) VALUES ('root', '123456');
INSERT INTO user (username, password) VALUES ('root', '123456');

DELETE FROM user;

CREATE TABLE favorite_folder (
  id INT AUTO_INCREMENT,
  name VARCHAR(32) UNIQUE DEFAULT NULL,
  `desc` VARCHAR(200) DEFAULT NULL,
  user_id INT,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user(id)
);

INSERT INTO favorite_folder (name, `desc`, user_id) VALUES ('MySQL', 'MySQL的优化十条小技巧', 5);


SELECT id, name, `desc` FROM favorite_folder WHERE user_id = 5;


DROP PROCEDURE IF EXISTS test;
DELIMITER #
CREATE PROCEDURE test()
BEGIN
  DECLARE i INT;
  SET i = 0;
  WHILE i < 1000000 DO
    INSERT INTO favorite_folder (name, `desc`, user_id) VALUES (CONCAT('NO.', i), 'MySQL的优化十条小技巧', 5);
    SET i = i + 1;
  END WHILE;
END #
DELIMITER ;
CALL test();
# delimter : mysql 默认的 delimiter是; 告诉mysql解释器，该段命令是否已经结束了，mysql是否可以执行了。
# 这里使用 delimiter 重定义结束符的作用是： 不让存储过程中的语句在定义的时候输出。

DELETE FROM favorite_folder;