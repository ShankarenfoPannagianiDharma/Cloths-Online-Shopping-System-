DROP TABLE users IF EXISTS;

CREATE TABLE users (
  email VARCHAR(50) PRIMARY KEY,
  name VARCHAR(50),
  password VARCHAR(20),
  admin int(2) DEFAULT 0
);