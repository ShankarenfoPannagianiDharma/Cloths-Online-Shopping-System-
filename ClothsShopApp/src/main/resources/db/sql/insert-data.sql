insert into users (id, email, username, password, admin) values (1, 'cgellan0@free.fr', 'Cherye Gellan', 'oK75gG', 0);
insert into users (id, email, username, password, admin) values (2, 'rberkely1@unesco.org', 'Raviv Berkely', 'gzJmsue4DW', 1);
insert into users (id, email, username, password, admin) values (3, 'vizzard2@vistaprint.com', 'Vicki Izzard', '6DRAnAv', 1);
insert into users (id, email, username, password, admin) values (4, 'lbambrough3@sina.com.cn', 'Lissi Bambrough', 'FiRGuMX', 1);
insert into users (id, email, username, password, admin) values (5, 'ncraft4@ft.com', 'Nisse Craft', 'xUsSU8F', 0);
insert into users (id, email, username, password, admin) values (6, 'pkinnock5@constantcontact.com', 'Pail Kinnock', 'inxPj9XU0d', 0);

insert into itemtypes (id, category) values (1, 'Shirt');
insert into itemtypes (id, category) values (2, 'Short');
insert into itemtypes (id, category) values (3, 'Suit');
insert into itemtypes (id, category) values (4, 'Dress');
insert into itemtypes (id, category) values (5, 'Shoes');

insert into itemsizes (id, size) values (1, 'XS');
insert into itemsizes (id, size) values (2, 'S');
insert into itemsizes (id, size) values (3, 'M');
insert into itemsizes (id, size) values (4, 'L');
insert into itemsizes (id, size) values (5, 'XL');

insert into products (id, name, price, type, stock, size, description, image) values (1, 'Name1', 27.45, 1, 6, 1, 'DescriptionPlaceHolder', 'jean.jpg');
insert into products (id, name, price, type, stock, size, description, image) values (2, 'Name1', 23.07, 1, 56, 2, 'DescriptionPlaceHolder', 'jean.jpg');
insert into products (id, name, price, type, stock, size, description, image) values (3, 'Name1', 18.36, 1, 69, 3, 'DescriptionPlaceHolder', 'jean.jpg');
insert into products (id, name, price, type, stock, size, description, image) values (4, 'Name1', 19.08, 1, 75, 4, 'DescriptionPlaceHolder', 'jean.jpg');
insert into products (id, name, price, type, stock, size, description, image) values (5, 'Name1', 25.33, 1, 100, 5, 'DescriptionPlaceHolder', 'jean.jpg');
insert into products (id, name, price, type, stock, size, description, image) values (6, 'Name1', 12.24, 1, 7, 1, 'DescriptionPlaceHolder', 'jean.jpg');
insert into products (id, name, price, type, stock, size, description, image) values (7, 'Name1', 26.08, 1, 81, 2, 'DescriptionPlaceHolder', 'jean.jpg');
insert into products (id, name, price, type, stock, size, description, image) values (8, 'Name1', 11.34, 2, 19, 3, 'DescriptionPlaceHolder', 'jean.jpg');
insert into products (id, name, price, type, stock, size, description, image) values (9, 'Name1', 28.34, 2, 70, 4, 'DescriptionPlaceHolder', 'jean.jpg');
insert into products (id, name, price, type, stock, size, description, image) values (10, 'Name1', 11.59, 2, 96, 5, 'DescriptionPlaceHolder', 'jean.jpg');

insert into orders (id, customerid, destination) values (1, 1, '4290 Cardinal Road');
insert into orders (id, customerid, destination) values (2, 2, '313 Acker Hill');
insert into orders (id, customerid, destination) values (3, 3, '23023 Delaware Drive');
insert into orders (id, customerid, destination) values (4, 4, '637 Straubel Park');
insert into orders (id, customerid, destination) values (5, 5, '4 Hermina Center');
insert into orders (id, customerid, destination) values (6, 6, '533 Norway Maple Drive');
insert into orders (id, customerid, destination) values (7, 1, '6354 Barnett Plaza');
insert into orders (id, customerid, destination) values (8, 2, '404 Lillian Avenue');
insert into orders (id, customerid, destination) values (9, 3, '0 Oneill Point');
insert into orders (id, customerid, destination) values (10, 4, '093 Di Loreto Center');

insert into orderdetails (orderid, itemid, amount, price) values (1, 1, 6, 28.13);
insert into orderdetails (orderid, itemid, amount, price) values (8, 7, 7, 27.19);
insert into orderdetails (orderid, itemid, amount, price) values (1, 7, 3, 24.09);
insert into orderdetails (orderid, itemid, amount, price) values (6, 7, 2, 22.56);
insert into orderdetails (orderid, itemid, amount, price) values (3, 5, 5, 18.20);
insert into orderdetails (orderid, itemid, amount, price) values (6, 1, 1, 26.44);
insert into orderdetails (orderid, itemid, amount, price) values (5, 2, 5, 22.95);
insert into orderdetails (orderid, itemid, amount, price) values (5, 7, 3, 12.90);
insert into orderdetails (orderid, itemid, amount, price) values (4, 3, 8, 19.52);
insert into orderdetails (orderid, itemid, amount, price) values (8, 8, 6, 26.08);