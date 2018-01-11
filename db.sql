CREATE TABLE user (
  id INT AUTO_INCREMENT,
  username VARCHAR(32) DEFAULT NULL,
  password VARCHAR(32) DEFAULT NULL,
  PRIMARY KEY (id)
);

INSERT INTO user (username, password) VALUES ('root', '123456');
