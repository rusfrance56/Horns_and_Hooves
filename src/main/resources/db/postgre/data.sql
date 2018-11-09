DELETE FROM order_items;
DELETE FROM customer_order;
DELETE FROM item;
DELETE FROM person_role;
DELETE FROM task;
DELETE FROM person;
DELETE FROM role;
ALTER SEQUENCE global_seq RESTART WITH 100100;

INSERT INTO person (id, name, middle_name, surname, address, phone, email, department, description) VALUES
  (100001, 'Сергей', 'Олегович', 'Синицын', 'Address', '89268501232', 'email1@mail.ru', 'OFFICE', 'description'),
  (100002, 'Егор', 'Павлович', 'Майоров', 'Address', '89268501232', 'email2@mail.ru', 'STORAGE', 'description'),
  (100003, 'Олег', 'Александрович', 'Петров', 'Address', '89268501232', 'email3@mail.ru', 'CUSHIONED', 'description'),
  (100004, 'Олег', 'Петрович', 'Клыков', 'Address', '89268501232', 'email4@mail.ru', null , 'description'),
  (100005, 'Павел', 'Иванович', 'Соболев', 'Address', '89268501232', 'email5@mail.ru', null , 'description'),
  (100006, 'Армен', 'Вахтангович', 'Гугаров', 'Address', '89268501232', 'email6@mail.ru', null, 'description');

INSERT INTO item (id, name, description, image_url, cost) VALUES
  (100007, 'Книжний шкаф', 'Книжный шкаф из красного дерева. Имееет выдвижные ящички по бокам и 3 полки', '/link/sfasfa/fasdf.jpg', 2343.12),
  (100008, 'Журнальный столик черный', 'Журнальный столик с добавлением пластиковых вставок и кожанной отделкой', '/link/sfasfa/fasdf.jpg', 1233.86),
  (100009, 'Стул', 'Стул удобный, с регулируемой спинкой и высотой посадки', '/link/sfasfa/fasdf.jpg', 856.32);

INSERT INTO customer_order (id, name, due_date, status, description, person_id) VALUES
  (100010, 'order1', '2018-12-19 08:17:00', 'CREATED', 'Сделайте пожалуйста хорошо!', 100006);

INSERT INTO order_items (customer_order_id, item_id) VALUES
  (100010, 100007),
  (100010, 100009);

INSERT INTO task (id, name, due_date, status, priority, person_id, department, description) VALUES
  (100011, 'Книжный шкаф задание', '2018-12-19 08:17:00', 'DEPLOYED', 'HIGH', 100002, 'STORAGE', 'Описание шкафа'),
  (100012, 'Стул офисный', '2018-12-19 08:17:00', 'DEPLOYED', 'LOW', 100001, 'OFFICE', 'Описание стула');

INSERT INTO role (id, name, description) VALUES
  (100013, 'customer', 'description'),
  (100014, 'worker', 'description'),
  (100015, 'manager', 'description'),
  (100016, 'admin', 'description');

INSERT INTO person_role (person_id, role_id) VALUES
  (100006, 100013),
  (100005, 100016),
  (100004, 100015),
  (100003, 100014),
  (100002, 100014),
  (100001, 100014);




