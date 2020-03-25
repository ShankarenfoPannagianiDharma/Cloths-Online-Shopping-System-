-- first 2 is test account 
insert into users (id, email, username, password, admin) values (1, 'user', 'Cherye Gellan', '1', FALSE);
insert into users (id, email, username, password, admin) values (2, 'admin', 'Raviv Berkely', '1', TRUE);
-- 
insert into users (id, email, username, password, admin) values (3, 'vizzard2@vistaprint.com', 'Vicki Izzard', '6DRAnAv', FALSE);
insert into users (id, email, username, password, admin) values (4, 'lbambrough3@sina.com.cn', 'Lissi Bambrough', 'FiRGuMX', FALSE);
insert into users (id, email, username, password, admin) values (5, 'ncraft4@ft.com', 'Nisse Craft', 'xUsSU8F', FALSE);
insert into users (id, email, username, password, admin) values (6, 'pkinnock5@constantcontact.com', 'Pail Kinnock', 'inxPj9XU0d', FALSE);

insert into itemtypes (id, category) values (1, 'Shirts');
insert into itemtypes (id, category) values (2, 'Shorts');
insert into itemtypes (id, category) values (3, 'Suits');
insert into itemtypes (id, category) values (4, 'Dresses');
insert into itemtypes (id, category) values (5, 'Shoes');
insert into itemtypes (id, category) values (6, 'Pants');

insert into itemsizes (id, size) values (1, 'XS');
insert into itemsizes (id, size) values (2, 'S');
insert into itemsizes (id, size) values (3, 'M');
insert into itemsizes (id, size) values (4, 'L');
insert into itemsizes (id, size) values (5, 'XL');

insert into products (id, name, price, type, stock, size, description, image) values (1, 'Jeans', 27.45, 5, 6, 1, 'This is a jeans', 'jean.jpg');
insert into products (id, name, price, type, stock, size, description, image) values (2, 'Name1', 23.07, 1, 56, 2, 'DescriptionPlaceHolder', 'jean.jpg');
insert into products (id, name, price, type, stock, size, description, image) values (3, 'Name2', 18.36, 1, 69, 3, 'DescriptionPlaceHolder', 'jean.jpg');
insert into products (id, name, price, type, stock, size, description, image) values (4, 'Name3', 19.08, 1, 75, 4, 'DescriptionPlaceHolder', 'jean.jpg');
insert into products (id, name, price, type, stock, size, description, image) values (5, 'Name4', 25.33, 1, 100, 5, 'DescriptionPlaceHolder', 'jean.jpg');
insert into products (id, name, price, type, stock, size, description, image) values (6, 'Name5', 12.24, 1, 7, 1, 'DescriptionPlaceHolder', 'jean.jpg');
insert into products (id, name, price, type, stock, size, description, image) values (7, 'Name6', 26.08, 1, 81, 2, 'DescriptionPlaceHolder', 'jean.jpg');
insert into products (id, name, price, type, stock, size, description, image) values (8, 'Name7', 11.34, 2, 19, 3, 'DescriptionPlaceHolder', 'jean.jpg');
insert into products (id, name, price, type, stock, size, description, image) values (9, 'Name8', 28.34, 2, 70, 4, 'DescriptionPlaceHolder', 'jean.jpg');
insert into products (id, name, price, type, stock, size, description, image) values (10, 'Name9', 11.59, 2, 96, 5, 'DescriptionPlaceHolder', 'jean.jpg');

insert into orders (id, customerid, destination) values (1, 1, '4290 Cardinal Road');
insert into orders (id, customerid, destination) values (2, 2, '313 Acker Hill');
insert into orders (id, customerid, destination) values (3, 3, '23023 Delaware Drive');
insert into orders (id, customerid, destination) values (4, 4, '637 Straubel Park');
insert into orders (id, customerid, destination) values (5, 5, '4 Hermina Center');
insert into orders (id, customerid, destination) values (6, 6, '533 Norway Maple Drive');
insert into orders (id, customerid, destination) values (7, 1, '4290 Cardinal Road');
insert into orders (id, customerid, destination) values (8, 2, '313 Acker Hill');
insert into orders (id, customerid, destination) values (9, 3, '23023 Delaware Drive');
insert into orders (id, customerid, destination) values (10, 4, '637 Straubel Park');

insert into orderdetails (orderid, itemid, amount, price) values (1, 1, 6, 0);
insert into orderdetails (orderid, itemid, amount, price) values (8, 7, 7, 0);
insert into orderdetails (orderid, itemid, amount, price) values (1, 7, 3, 0);
insert into orderdetails (orderid, itemid, amount, price) values (6, 7, 2, 0);
insert into orderdetails (orderid, itemid, amount, price) values (3, 5, 5, 0);
insert into orderdetails (orderid, itemid, amount, price) values (6, 1, 1, 0);
insert into orderdetails (orderid, itemid, amount, price) values (5, 2, 5, 0);
insert into orderdetails (orderid, itemid, amount, price) values (5, 7, 3, 0);
insert into orderdetails (orderid, itemid, amount, price) values (4, 3, 8, 0);
insert into orderdetails (orderid, itemid, amount, price) values (8, 8, 6, 0);