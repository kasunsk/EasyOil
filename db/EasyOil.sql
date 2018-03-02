CREATE DATABASE easy_oil;

USE easy_oil;

CREATE TABLE SELLING_ITEM (
  ITEM_ID INT NOT NULL auto_increment,
  ITEM_REFERENCE VARCHAR(30) NOT NULL UNIQUE,
  ITEM_NAME VARCHAR(30) NOT NULL,
  AVAILABLE_AMOUNT INT NOT NULL,
  DESCRIPTION VARCHAR(60) NOT NULL,
  STATUS VARCHAR(15) NOT NULL,
  PRICE INT NOT NULL,
  CURRENCY VARCHAR(5) NOT NULL ,
  IMAGE_URL VARCHAR(60) NOT NULL ,
  VALID_TO TIMESTAMP,
  CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  LAST_MODIFIED_DATE TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
  VERSION INT(3),
  PRIMARY KEY (ITEM_ID)
);

INSERT INTO SELLING_ITEM VALUES (1, 'abcd', 'Shesha Hair Oil', 25, 'This is only for hair','AVAILABLE', 1490, 'Rs','image_url', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL , 0);

INSERT INTO SELLING_ITEM VALUES (2, 'sdca', 'Sandi Sudha', 20, 'This is only for skin','AVAILABLE', 3490, 'Rs','image_url',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, NULL , 0);

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
);

-- INSERT INTO CUSTOMER VALUES (1, 'Kasun Kariyawasam', '0719751748', 'kasun@mail.com','Shanthi Sevana, Jangulla, Hali Ela',CURRENT_TIMESTAMP,NULL ,0);


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
);

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
);

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
);