DELETE FROM orders_items;
DELETE FROM tasks;
DELETE FROM customer_orders;
DELETE FROM item_images;
DELETE FROM items;
DELETE FROM users_roles;
DELETE FROM roles_permissions;
DELETE FROM roles;
DELETE FROM permissions;
DELETE FROM users;

INSERT INTO users (id, logon_name, password, name, surname, address, phone, email, department, description) VALUES
  (100001, 'user1', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Сергей', 'Синицын', '05096 Lotheville Crossing', '+7 (152) 446-1962', 'tbridge0@howstuffworks.com', 'OFFICE', 'description'),
  (100002, 'user2', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Егор', 'Майоров', '09570 Mcguire Trail', '+234 (282) 175-0310', 'cundrell1@go.com', 'STORAGE', 'description'),
  (100003, 'user3', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Олег', 'Петров', '615 Elka Parkway', '+7 (744) 572-8930', 'wconeybeer2@cbc.ca', 'OFFICE', 'description'),
  (100004, 'user4', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Олег', 'Клыков', '020 Namekagon Circle', '+992 (137) 353-5351', 'sianitti3@domainmarket.com', 'CUSHIONED', 'description'),
  (100005, 'user5', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Павел', 'Соболев', '6 Carioca Street', '+86 (317) 469-2821', 'elohde6@booking.com', 'STORAGE', 'description'),
  (100006, 'user6', '$2a$12$HG/H3BcXMMD.F9mKPxB0o.3civXRcLH997LZv2Q30JexRWXah7HLq', 'Армен', 'Гугаров', '68 Logan Point', '+353 (757) 489-9946', 'asimmens4@freewebs.com', null, 'description');

INSERT INTO items (id, name, description, department, price) VALUES
  (100007, 'Стенка Версаль', 'Версаль СБ-1655 Стенка', 'STORAGE', 38490.90),
  (100008, 'Диван угловой', 'Диван угловой Атланта Классик со столом, 145, рогожка, дельфин', 'CUSHIONED', 26080),
  (100009, 'Комод Медея', 'Медея СБ-2454 Комод', 'STORAGE', 9490),
  (100017, 'Кухня Регина серия Mishel', 'Кухня Регина серия Mishel Беж песок/Ирландский ликер, Рустик серый 1,8-2,4 м (Мишель)', 'STORAGE', 110856.00),
  (100018, 'Шкаф-купе', 'Терра СБ-2545 Шкаф-купе 2-х дверный', 'STORAGE', 8590.00),
  (100019, 'Диван-кровать угловой', 'Диван-кровать угловой еврокнижка Модерн,велюр', 'CUSHIONED', 42990.99),
  (100020, 'Шкаф 2-х дверный', 'Мамбо СБ-2371 Шкаф 2-х дверный', 'STORAGE', 9890.00),
  (100021, 'Кухня Деми', 'Кухня Деми Белый / Дуб Сонома 1 м', 'STORAGE', 9990),
  (100022, 'Стол-тумба', 'Мамбо СБ-2886 Стол-тумба', 'OFFICE', 6890),
  (100023, 'Стенка Прадо', 'Стенка Прадо (Вегас) СБ-2224', 'OFFICE', 19790);

INSERT INTO customer_orders (id, name, due_date, status, description, user_id) VALUES
  (100010, 'order1', '2018-12-19 08:17:00', 'CREATED', 'Сделайте пожалуйста хорошо!', 100006);

INSERT INTO orders_items (customer_order_id, item_id) VALUES
  (100010, 100007),
  (100010, 100022);

INSERT INTO tasks (id, name, due_date, status, priority, user_id, department, description, item_id, order_id) VALUES
  (100011, 'Стенка', '2018-12-19 08:17:00', 'DEPLOYED', 'HIGH', 100002, 'STORAGE', 'Описание стенки', 100007, 100010),
  (100012, 'Стол-тумба', '2018-12-19 08:17:00', 'DEPLOYED', 'LOW', 100001, 'OFFICE', 'Описание стола-тумбы', 100022, 100010);

INSERT INTO roles (id, name) VALUES
  (100013, 'ROLE_USER'),
  (100014, 'ROLE_ADMIN');

INSERT INTO permissions (id, name) VALUES
  (100015, 'READ'),
  (100016, 'WRITE');

INSERT INTO roles_permissions (role_id, permission_id) VALUES
  (100013, 100015),
  (100014, 100015),
  (100014, 100016);

INSERT INTO users_roles (user_id, role_id) VALUES
  (100001, 100013),
  (100001, 100014),
  (100002, 100013),
  (100003, 100013),
  (100004, 100013),
  (100005, 100013),
  (100006, 100013);

