DELETE FROM order_items;
DELETE FROM customer_order;
DELETE FROM item;
DELETE FROM person_roles;
DELETE FROM task;
DELETE FROM person;
ALTER SEQUENCE global_seq RESTART WITH 100100;

INSERT INTO person (id, name, middle_name, surname, address, phone, email, department, description) VALUES
  (100001, 'Сергей', 'Олегович', 'Синицын', 'Address', '89268501232', 'email@mail.ru', 'OFFICE', 'description'),
  (100002, 'Егор', 'Павлович', 'Майоров', 'Address', '89268501232', 'email@mail.ru', 'STORAGE', 'description'),
  (100003, 'Олег', 'Александрович', 'Петров', 'Address', '89268501232', 'email@mail.ru', 'OFFICE', 'description'),
  (100004, 'Олег', 'Петрович', 'Клыков', 'Address', '89268501232', 'email@mail.ru', 'CUSHIONED', 'description'),
  (100005, 'Павел', 'Иванович', 'Соболев', 'Address', '89268501232', 'email@mail.ru', 'STORAGE', 'description'),
  (100006, 'Армен', 'Вахтангович', 'Гугаров', 'Address', '89268501232', 'email@mail.ru', null, 'description');

INSERT INTO item (id, name, description, imageurl, cost) VALUES
  (100007, 'Книжний шкаф', 'Книжный шкаф из красного дерева. Имееет выдвижные ящички по бокам и 3 полки', '/link/sfasfa/fasdf.jpg', 2343.12),
  (100008, 'Журнальный столик черный', 'Журнальный столик с добавлением пластиковых вставок и кожанной отделкой', '/link/sfasfa/fasdf.jpg', 1233.86),
  (100009, 'Стул', 'Стул удобный, с регулируемой спинкой и высотой посадки', '/link/sfasfa/fasdf.jpg', 856.32);

INSERT INTO customer_order (id, name, due_date, status, description, person_id) VALUES
  (100010, 'order1', '2018-12-19 08:17:00', 'CREATED', 'Сделайте пожалуйста хорошо!', 100006);

INSERT INTO order_items (customer_order_id, item_id) VALUES
  (100010, 100007),
  (100010, 100009);

INSERT INTO task (id, name, due_date, is_assigned, status, priority, person_id, department, description) VALUES
  (100011, 'Книжный шкаф задание', '2018-12-19 08:17:00', true, 'DEPLOYED', 'HIGH', 100002, 'STORAGE', 'Описание шкафа'),
  (100012, 'Стул офисный', '2018-12-19 08:17:00', true, 'DEPLOYED', 'LOW', 100001, 'OFFICE', 'Описание стула');


