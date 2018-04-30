CREATE DATABASE easy_oil;

USE easy_oil;

CREATE TABLE PRODUCT (
  ITEM_ID INT NOT NULL auto_increment,
  ITEM_REFERENCE VARCHAR(30) NOT NULL UNIQUE,
  ITEM_NAME VARCHAR(30) NOT NULL,
  AVAILABLE_AMOUNT INT NOT NULL,
  DESCRIPTION VARCHAR(500) NOT NULL,
  STATUS VARCHAR(15) NOT NULL,
  PRICE INT NOT NULL,
  CURRENCY VARCHAR(5) NOT NULL ,
  IMAGE_URL VARCHAR(200) NOT NULL ,
  VALID_TO TIMESTAMP,
  CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  LAST_MODIFIED_DATE TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
  VERSION INT(3),
  PRIMARY KEY (ITEM_ID)
)ENGINE=InnoDB;

INSERT INTO PRODUCT VALUES (1, 'abcd', 'Shesha Hair Oil', 25, 'This is only for hair','AVAILABLE', 1490, 'LKR','http://2.bp.blogspot.com/-NYEfYYLAJNA/VbpniMdwCOI/AAAAAAAAf0M/P-NkPJutW5M/s500/Oshea%2BHerbals%2BPhytogain%2BHair%2BVitalizer%2BReview%252C%2BPrice%252C%2BHow%2Bto%2BUse.jpg', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL , 0);

INSERT INTO PRODUCT VALUES (2, 'sdca', 'Sandi Sudha', 20, 'This is only for skin','AVAILABLE', 3490, 'LKR','https://i.pinimg.com/originals/39/fe/94/39fe9461d5dd3655b973d62026ea5b7a.jpg',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL , 0);

CREATE TABLE CUSTOMER (
  CUSTOMER_ID INT NOT NULL auto_increment,
  CUSTOMER_NAME VARCHAR(30) NOT NULL,
  MOBILE_NUMBER VARCHAR(30) NOT NULL,
  EMAIL VARCHAR(30) NOT NULL,
  ADDRESS VARCHAR(60) NOT NULL,
  CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  LAST_MODIFIED_DATE TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
  VERSION INT(3),
  PRIMARY KEY (CUSTOMER_ID)
)ENGINE=InnoDB;

INSERT INTO CUSTOMER VALUES (1, 'Test Customer', '01111', 'test@mail.com','Test Address',CURRENT_TIMESTAMP,NULL ,0);

CREATE TABLE PLACED_ORDER (
  ORDER_ID INT NOT NULL auto_increment,
  ORDER_REFERENCE VARCHAR(30) NOT NULL UNIQUE,
  PAYMENT_REFERENCE VARCHAR(30),
  ORDER_ITEM VARCHAR(50) NOT NULL,
  AMOUNT INT(5) NOT NULL,
  PRICE INT(20) NOT NULL,
  CURRENCY VARCHAR(30) NOT NULL,
  PAYMENT_TYPE VARCHAR(30) NOT NULL,
  PAYMENT_STATUS VARCHAR(30) NOT NULL,
  CUSTOMER_ID INT(10) NOT NULL,
  STATUS VARCHAR(30) NOT NULL,
  CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  LAST_MODIFIED_DATE TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
  VERSION INT(3),
  PRIMARY KEY (ORDER_ID)
) ENGINE=InnoDB;

INSERT INTO PLACED_ORDER VALUES (1, 'abcd','COD','Product Item', 1,3000,'LKR','CASH','WAITING', 1, 'PLACED',CURRENT_TIMESTAMP, NULL ,0);

CREATE TABLE HISTORY_ITEM (
  HISTORY_ID INT NOT NULL auto_increment,
  ORDER_ID INT (5),
  SELLING_ITEM_ID INT (3),
  HISTORY_TYPE VARCHAR(40) NOT NULL,
  FROM_STATUS VARCHAR(40),
  TO_STATUS VARCHAR(40),
  NOTE VARCHAR(80) NOT NULL,
  USER VARCHAR(20) NOT NULL,
  CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  LAST_MODIFIED_DATE TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
  VERSION INT(3),
  PRIMARY KEY (HISTORY_ID)
)ENGINE=InnoDB;

INSERT INTO HISTORY_ITEM VALUES (1, 1, 1, 'ORDER_CREATED',NULL ,NULL ,'Test Note','DB', CURRENT_TIMESTAMP,NULL ,0);

CREATE TABLE USER (
  ID INT NOT NULL auto_increment,
  ROLE VARCHAR (10),
  NAME VARCHAR(40) NOT NULL,
  USERNAME VARCHAR(40) NOT NULL UNIQUE,
  PASSWORD VARCHAR(40) NOT NULL,
  MOBILE VARCHAR(80) NOT NULL,
  EMAIL VARCHAR(20) NOT NULL,
  CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  LAST_MODIFIED_DATE TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
  VERSION INT(3),
  PRIMARY KEY (ID)
)ENGINE=InnoDB;

INSERT INTO USER VALUES (1,'ADMIN', 'Admin Admin', 'admin', 'admin@123','011254565', 'kasunsk@gmail.com', CURRENT_TIMESTAMP, NULL ,0);

CREATE TABLE USER_TOKEN(
  TOKEN_ID INT NOT NULL auto_increment,
  NO_OF_REQUEST INT(5) NOT NULL,
  USER_ID INT(5) NOT NULL,
  USER_NAME VARCHAR(20) NOT NULL,
  USER_TOKEN VARCHAR(80) NOT NULL UNIQUE ,
  TOKEN_STATUS VARCHAR(10) NOT NULL,
  CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  LAST_MODIFIED_DATE TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
  VERSION INT(3),
  PRIMARY KEY (TOKEN_ID)
)ENGINE=InnoDB;