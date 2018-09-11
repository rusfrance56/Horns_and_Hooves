DELETE FROM order_items;
DELETE FROM customer_order;
DELETE FROM item;
DELETE FROM person_roles;
DELETE FROM task;
DELETE FROM person;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO person (name, middle_name, surname, address, phone, email, department, description) VALUES
  ('Сергей', 'Олегович', 'Синицын', 'Address', '89268501232', 'email@mail.ru', 'OFFICE', 'description'),
  ('Егор', 'Павлович', 'Майоров', 'Address', '89268501232', 'email@mail.ru', 'STORAGE', 'description'),
  ('Олег', 'Александрович', 'Петров', 'Address', '89268501232', 'email@mail.ru', 'OFFICE', 'description'),
  ('Олег', 'Петрович', 'Клыков', 'Address', '89268501232', 'email@mail.ru', 'CUSHIONED', 'description'),
  ('Павел', 'Иванович', 'Соболев', 'Address', '89268501232', 'email@mail.ru', 'STORAGE', 'description'),
  ('Армен', 'Вахтангович', 'Гугаров', 'Address', '89268501232', 'email@mail.ru', 'OFFICE', 'description');
/*INSERT INTO order_furniture (name, date, is_assigned, employee_id, department_id) VALUES
  ('Кожаный диван', '2017-10-11 15:36:38', 'true', 100003, 100000),
  ('Кожаный стул', '2017-10-22 17:59:38', 'true', 100003, 100000),
  ('Кожаный стул', '2017-10-12 12:00:00', 'true', 100005, 100000),
  ('Книжная полка', '2017-11-08 12:30:00', 'true', 100006, 100001),
  ('Книжная полка', '2017-11-08 12:30:00', 'true', 100007, 100001),
  ('Кресло директора', '2017-11-12 14:38:00', 'true', 100008, 100002);*/

