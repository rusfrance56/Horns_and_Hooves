DROP TABLE IF EXISTS order_furniture;
DROP TABLE IF EXISTS employee_roles;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE department (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  created       TIMESTAMP           DEFAULT now(),
  name          VARCHAR             NOT NULL
);
CREATE TABLE employee (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  created       TIMESTAMP           DEFAULT now(),
  name          VARCHAR             NOT NULL,
  department_id INTEGER             NOT NULL,
  middle_name   VARCHAR             DEFAULT NULL,
  sur_name      VARCHAR             NOT NULL,
  FOREIGN KEY (department_id) REFERENCES department (id) ON DELETE CASCADE
);
CREATE TABLE order_furniture (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  created       TIMESTAMP           DEFAULT now(),
  name          VARCHAR             NOT NULL,
  due_date      TIMESTAMP           DEFAULT now(),
  is_assigned   BOOL                DEFAULT FALSE,
  status        VARCHAR             NOT NULL,
  priority      VARCHAR             NOT NULL,
  employee_id   INTEGER             DEFAULT NULL,
  department_id INTEGER             NOT NULL,
  FOREIGN KEY (employee_id) REFERENCES employee (id),
  FOREIGN KEY (department_id) REFERENCES department (id) ON DELETE CASCADE
);
CREATE TABLE employee_roles(
  employee_id   INTEGER             NOT NULL,
  role          VARCHAR,
  CONSTRAINT employee_roles_idx UNIQUE (employee_id, role),
  FOREIGN KEY (employee_id) REFERENCES employee (id) ON DELETE CASCADE
);