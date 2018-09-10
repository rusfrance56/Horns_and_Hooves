DELETE FROM order_furniture;
DELETE FROM person;
DELETE FROM department;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO department (name) VALUES
  ('CUSHIONED'),
  ('STORAGE'),
  ('OFFICE');
INSERT INTO person (name, department_id, middle_name, sur_name) VALUES
  ('Сергей', 100000, 'Олегович', 'Синицын'),
  ('Егор', 100000, 'Павлович', 'Майоров'),
  ('Олег', 100000, 'Александрович', 'Петров'),
  ('Олег', 100001, 'Петрович', 'Клыков'),
  ('Павел', 100001, 'Иванович', 'Соболев'),
  ('Армен', 100002, 'Вахтангович', 'Гугаров');
INSERT INTO order_furniture (name, date, is_assigned, employee_id, department_id) VALUES
  ('Кожаный диван', '2017-10-11 15:36:38', 'true', 100003, 100000),
  ('Кожаный стул', '2017-10-22 17:59:38', 'true', 100003, 100000),
  ('Кожаный стул', '2017-10-12 12:00:00', 'true', 100005, 100000),
  ('Книжная полка', '2017-11-08 12:30:00', 'true', 100006, 100001),
  ('Книжная полка', '2017-11-08 12:30:00', 'true', 100007, 100001),
  ('Кресло директора', '2017-11-12 14:38:00', 'true', 100008, 100002);

