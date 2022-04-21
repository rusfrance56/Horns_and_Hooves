DELETE FROM orders_items;
DELETE FROM customer_orders;
DELETE FROM items;
DELETE FROM users_roles;
DELETE FROM roles_permissions;
DELETE FROM roles;
DELETE FROM permissions;
DELETE FROM tasks;
DELETE FROM users;

INSERT INTO users (id, logon_name, password, name, surname, address, phone, email, department, description) VALUES
  (100001, 'user1', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Сергей', 'Синицын', '05096 Lotheville Crossing', '+7 (152) 446-1962', 'tbridge0@howstuffworks.com', 'OFFICE', 'description'),
  (100002, 'user2', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Егор', 'Майоров', '09570 Mcguire Trail', '+234 (282) 175-0310', 'cundrell1@go.com', 'STORAGE', 'description'),
  (100003, 'user3', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Олег', 'Петров', '615 Elka Parkway', '+7 (744) 572-8930', 'wconeybeer2@cbc.ca', 'OFFICE', 'description'),
  (100004, 'user4', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Олег', 'Клыков', '020 Namekagon Circle', '+992 (137) 353-5351', 'sianitti3@domainmarket.com', 'CUSHIONED', 'description'),
  (100005, 'user5', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Павел', 'Соболев', '6 Carioca Street', '+86 (317) 469-2821', 'elohde6@booking.com', 'STORAGE', 'description'),
  (100006, 'user6', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Армен', 'Гугаров', '68 Logan Point', '+353 (757) 489-9946', 'asimmens4@freewebs.com', null, 'description');

INSERT INTO items (id, name, description, image_url, cost) VALUES
  (100007, 'Книжний шкаф', 'Книжный шкаф из красного дерева. Имееет выдвижные ящички по бокам и 3 полки', '/link/sfasfa/fasdf.jpg', 2343.12),
  (100008, 'Журнальный столик черный', 'Журнальный столик с добавлением пластиковых вставок и кожанной отделкой', '/link/sfasfa/fasdf.jpg', 1233.86),
  (100009, 'Стул', 'Стул удобный, с регулируемой спинкой и высотой посадки', '/link/sfasfa/fasdf.jpg', 856.32);

INSERT INTO customer_orders (id, name, due_date, status, description, user_id) VALUES
  (100010, 'order1', '2018-12-19 08:17:00', 'CREATED', 'Сделайте пожалуйста хорошо!', 100006);

INSERT INTO orders_items (customer_order_id, item_id) VALUES
  (100010, 100007),
  (100010, 100009);

INSERT INTO tasks (id, name, due_date, status, priority, user_id, department, description) VALUES
  (100011, 'Книжный шкаф задание', '2018-12-19 08:17:00', 'DEPLOYED', 'HIGH', 100002, 'STORAGE', 'Описание шкафа'),
  (100012, 'Стул офисный', '2018-12-19 08:17:00', 'DEPLOYED', 'LOW', 100001, 'OFFICE', 'Описание стула');

INSERT INTO roles (id, name) VALUES
  (100013, 'ROLE_USER'),
  (100014, 'ROLE_ADMIN');

INSERT INTO users_roles (user_id, role_id) VALUES
  (100001, 100013),
  (100001, 100014),
  (100002, 100013),
  (100003, 100013),
  (100004, 100013),
  (100005, 100013),
  (100006, 100013);

