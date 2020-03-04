CREATE DATABASE IF NOT EXISTS usernamesandpasswords;
USE usernamesandpasswords;

-- Creates the users table
DROP TABLE IF EXISTS users;
CREATE TABLE users(
  userID INT NOT NULL AUTO_INCREMENT,
  firstName VARCHAR(35),
  lastName VARCHAR(35),
  phoneNumber CHAR(12),
  PRIMARY KEY(userID)
);

-- Creates the category table
DROP TABLE IF EXISTS category;
CREATE TABLE category(
  categoryID INT AUTO_INCREMENT PRIMARY KEY,
  categoryName CHAR(15)
);

-- Creates the credentials table
DROP TABLE IF EXISTS credentials;
CREATE TABLE credentials(
  numOfCreds INT AUTO_INCREMENT,
  userID INT NOT NULL,
  websiteName VARCHAR(25),
  username VARCHAR(35),
  password VARCHAR(50),
  dateCreated DATE,
  currentLogin BOOL,
  categoryID INT,
  PRIMARY KEY(numOfCreds),
  FOREIGN KEY(userID) REFERENCES users(userID),
  FOREIGN KEY(categoryID) REFERENCES category(categoryID)
);

-- Inserts sample data into the table
INSERT INTO users(userID, firstName, lastName, phoneNumber)
VALUES(1, "Antonio", "Ocasio", 8152957629);

INSERT INTO users(userID, firstName, lastName, phoneNumber)
VALUES(2, "John", "Smith", 3274387727);

INSERT INTO users(userID, firstName, lastName, phoneNumber)
VALUES(3, "Jason", "Borne", 4527520389);

INSERT INTO category(categoryID, categoryName)
VALUES(1, "Email");

INSERT INTO category(categoryID, categoryName)
VALUES(2, "Social Media");

INSERT INTO category(categoryID, categoryName)
VALUES(3, "Entertainment");

INSERT INTO credentials(userID, websiteName, username, password,
	dateCreated, currentLogin, categoryID)
VALUES(1, "twitter", "tony123", "password1", "1999-02-09", false, 2);

INSERT INTO credentials(userID, websiteName, username, password,
	dateCreated, currentLogin, categoryID)
VALUES(2, "twitter", "tony123", "password2", "2018-02-09", true, 2);

-- Creates the users
CREATE USER 'tony'@'localhost' identified by 'tcasio';
GRANT ALL ON usernamesandpasswords.* TO 'tony'@'localhost';

CREATE USER 'johnsmith'@'localhost' identified by 'smith';
GRANT SELECT, INSERT, UPDATE, DELETE ON usernamesandpasswords.* TO 'johnsmith'@'localhost';

CREATE USER 'drhoward'@'localhost' identified by 'howardcy';
GRANT ALL ON usernamesandpasswords.* TO 'drhoward'@'localhost';