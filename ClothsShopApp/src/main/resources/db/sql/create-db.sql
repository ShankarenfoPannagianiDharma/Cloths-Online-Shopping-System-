DROP TABLE orderdetails IF EXISTS;
DROP TABLE orders IF EXISTS;
DROP TABLE products IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE itemtypes IF EXISTS;
DROP TABLE itemsizes IF EXISTS;

-- Users have unique emails and usernames. admin int defines if user goes to admin page or customer page.
-- if admin value == 1
CREATE TABLE users (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(50) NOT NULL,
  username VARCHAR(50) NOT NULL,
  password VARCHAR(20) NOT NULL,
  admin INTEGER DEFAULT 0
);

-- ItemTypes defines the categories items are divided into. ID is automatically incremented
CREATE TABLE itemtypes (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  category VARCHAR(20) NOT NULL
);

-- itemSizes defines the size values and item can have.
CREATE TABLE itemsizes (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  size VARCHAR(5) NOT NULL
);

-- products defines the things this shop sells. has price and stock, references itemtypes for its type, itemsizes for size.
-- contains string description of item and BLOB value of image.
CREATE TABLE products (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  price DOUBLE NOT NULL,
  type INTEGER,
  stock INTEGER NOT NULL DEFAULT 0,
  size INTEGER,
  description VARCHAR(255),
  image VARCHAR(100),
  FOREIGN KEY (type) REFERENCES itemtypes(id),
  FOREIGN KEY (size) REFERENCES itemsizes(id)
);

-- orders contains the shopping checkouts of customers. has auto-generated id, customer referencing the username.
-- also has destination
CREATE TABLE orders (
  id INTEGER AUTO_INCREMENT PRIMARY KEY,
  customerid INTEGER,
  destination VARCHAR(255),
  FOREIGN KEY (customerid) REFERENCES users(id)
);

-- order details describes the items an order contains. has item reference, amount, cost at time of sale
CREATE TABLE orderdetails(
  orderid INTEGER, 
  itemid INTEGER,
  amount INTEGER DEFAULT 1,
  price DOUBLE NOT NULL,
  FOREIGN KEY (orderid) REFERENCES orders(id),
  FOREIGN KEY (itemid) REFERENCES products(id)
);