CREATE TABLE User(
   uid  INTEGER PRIMARY KEY,
   account  CHAR(128)  NOT NULL,
   sex  INT  NOT NULL,
   slogan VARCHAR(512),
   details VARCHAR(1024),
   avatar VARCHAR(1024),
   mobile CHAR(64),
   extra VARCHAR(1024),
   pwd CHAR(256) NOT NULL,
   state INT NOT NULL,
   age INT,
   createTime BIGINT,
   updateTime BIGINT
);
