DROP TABLE users IF EXISTS;

-- Users have unique emails and usernames. admin int defines if user goes to admin page or customer page.
CREATE TABLE users (
  email VARCHAR(50) NOT NULL UNIQUE,
  username VARCHAR(50) NOT NULL PRIMARY KEY,
  password VARCHAR(20) NOT NULL,
  admin int(2) DEFAULT 0
);
