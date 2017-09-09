CREATE DATABASE mailer CHARACTER SET utf8 COLLATE utf8_general_ci;

  CREATE TABLE users(
  ID INT NOT NULL AUTO_INCREMENT,
  LOGIN VARCHAR(30) NOT NULL,
  PASSWORD VARCHAR(30) NOT NULL,
  EMAIL VARCHAR(30) NOT NULL,
  STATUS TINYINT(1) NOT NULL,
  UNIQUE (LOGIN),
  UNIQUE (EMAIL),
  PRIMARY KEY(ID)
  );

  CREATE TABLE role(
  ROLE_ID VARCHAR(30)  NOT NULL,
  PRIMARY KEY(ROLE_ID)
  );

  CREATE TABLE user_role_detail(
  USER_ID INT NOT NULL,
  ROLE_ID VARCHAR(30)  NOT NULL,
  PRIMARY KEY (USER_ID,ROLE_ID),
  CONSTRAINT FK_USER_ROLE_DETAIL_1 FOREIGN KEY (USER_ID)
  REFERENCES users (ID) ON DELETE CASCADE,
  CONSTRAINT FK_USER_ROLE_DETAIL_2 FOREIGN KEY (ROLE_ID)
  REFERENCES role (ROLE_ID)
  );

CREATE TABLE mail_address(
  ID INT NOT NULL AUTO_INCREMENT,
  USER_ID INT NOT NULL,
  EMAIL VARCHAR(100)  NOT NULL,
  CONSTRAINT FK_USER FOREIGN KEY (USER_ID) REFERENCES users(ID),
  UNIQUE (USER_ID,EMAIL),
  PRIMARY KEY(ID)
);

CREATE TABLE mail_server(
  ID INT NOT NULL AUTO_INCREMENT,
  USER_ID INT NOT NULL,
  HOST VARCHAR(100)  NOT NULL,
  PORT INT NOT NULL,
  USERNAME VARCHAR(100)  NOT NULL,
  PASSWORD VARCHAR(100)  NOT NULL,
  SENDER VARCHAR(100)  NOT NULL,
  PROTOCOL VARCHAR(20)  NOT NULL,
  SMTPAUTH TINYINT(1) NOT NULL,
  FOREIGN KEY (USER_ID) REFERENCES users(ID),
  UNIQUE (USER_ID,HOST,PORT),
  PRIMARY KEY(ID)
 );

CREATE TABLE group_mail_address(
 ID INT NOT NULL AUTO_INCREMENT,
 USER_ID INT NOT NULL,
 TITLE VARCHAR(100) NOT NULL,
 FOREIGN KEY (USER_ID) REFERENCES users(ID),
 UNIQUE (USER_ID,NAME),
 PRIMARY KEY(ID)
);

CREATE TABLE group_mail_server(
 ID INT NOT NULL AUTO_INCREMENT,
 USER_ID INT NOT NULL,
 TITLE VARCHAR(100) NOT NULL,
 FOREIGN KEY (USER_ID) REFERENCES users(ID),
 UNIQUE (USER_ID,NAME),
 PRIMARY KEY(ID)
);


CREATE TABLE group_mail_address_detail(
  ID INT NOT NULL AUTO_INCREMENT,
  GROUP_ID INT NOT NULL,
  EMAIL_ID INT NOT NULL,
  CONSTRAINT FK_GROUP_MAIL_ADDRESS_DETAIL_1 FOREIGN KEY (GROUP_ID)
  REFERENCES group_mail_address (ID) ON DELETE CASCADE,
  CONSTRAINT FK_GROUP_MAIL_ADDRESS_DETAIL_2 FOREIGN KEY (EMAIL_ID)
  REFERENCES mail_address (ID),
  UNIQUE (GROUP_ID,EMAIL_ID),
  PRIMARY KEY (ID)
);

CREATE TABLE group_mail_server_detail(
  ID INT NOT NULL AUTO_INCREMENT,
  USER_ID INT NOT NULL,
  GROUP_ID INT NOT NULL,
  SERVER_ID INT NOT NULL,
  CONSTRAINT FK_GROUP_MAIL_SERVER_DETAIL_1 FOREIGN KEY (GROUP_ID)
  REFERENCES group_mail_server (ID) ON DELETE CASCADE,
  CONSTRAINT FK_GROUP_MAIL_SERVER_DETAIL_2 FOREIGN KEY (SERVER_ID)
  REFERENCES mail_server (ID),
  UNIQUE (GROUP_ID,SERVER_ID),
  PRIMARY KEY (ID)
);

CREATE TABLE entity(
  ID INT NOT NULL AUTO_INCREMENT,
  TITLE VARCHAR(50) NOT NULL,
  USER_ID INT NOT NULL,
  GROUP_EMAIL_ID INT NOT NULL,
  GROUP_SERVER_ID INT NOT NULL,
  MESSAGE_ID INT NOT NULL,
  COUNT INT NOT NUll DEFAULT 0,
  FOREIGN KEY (USER_ID) REFERENCES users(ID),
  CONSTRAINT FK_GROUP_EMAIL FOREIGN KEY (GROUP_EMAIL_ID)
  REFERENCES group_mail_address (ID),
  CONSTRAINT FK_GROUP_SERVER FOREIGN KEY (GROUP_SERVER_ID)
  REFERENCES group_mail_server (ID),
  CONSTRAINT FK_GROUP_MESSAGE FOREIGN KEY (MESSAGE_ID)
  REFERENCES entity (ID),
  STATUS TINYINT(1) NOT NULL,
  UNIQUE (USER_ID,TITLE),
  PRIMARY KEY(ID)
  );

 CREATE TABLE entity(
 ID INT NOT NULL AUTO_INCREMENT,
 TITLE VARCHAR(50) NOT NULL,
 USER_ID INT NOT NULL,
 TEXT VARCHAR(10000) NOT NULL,
 FOREIGN KEY (USER_ID) REFERENCES users(ID),
 UNIQUE (USER_ID,TITLE),
 PRIMARY KEY(ID)
);

CREATE TABLE schedule(
 ID INT NOT NULL AUTO_INCREMENT,
 TITLE VARCHAR(50) NOT NULL,
 USER_ID INT NOT NULL,
 PRIMARY KEY(ID)
);

