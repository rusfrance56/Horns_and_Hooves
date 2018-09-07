DROP TABLE IF EXISTS customer_order;
DROP TABLE IF EXISTS employee_roles;
DROP TABLE IF EXISTS person;
DROP TABLE IF EXISTS department;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE department (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  created       TIMESTAMP           DEFAULT now(),
  name          VARCHAR             NOT NULL
);
CREATE TABLE person (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  created       TIMESTAMP           DEFAULT now(),
  name          VARCHAR             NOT NULL,
  surname       VARCHAR             NOT NULL,
  middle_name   VARCHAR,
  address       VARCHAR             NOT NULL,
  phone         INTEGER             NOT NULL,
  email         VARCHAR             NOT NULL,
  department_id INTEGER,
  FOREIGN KEY (department_id) REFERENCES department (id)
--   ON DELETE CASCADE
);
CREATE TABLE customer_order (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  created       TIMESTAMP           DEFAULT now(),
  name          VARCHAR             NOT NULL,
  due_date      TIMESTAMP           DEFAULT now(),
  status        VARCHAR             NOT NULL,
  priority      VARCHAR             NOT NULL,
);
CREATE TABLE item (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  created       TIMESTAMP           DEFAULT now(),
  name          VARCHAR             NOT NULL
);
CREATE TABLE order_items (
  customer_order_id     INTEGER             NOT NULL,
  item_id               VARCHAR,
  CONSTRAINT order_items_idx UNIQUE (customer_order_id, item_id),
  FOREIGN KEY (customer_order_id) REFERENCES customer_order (id),
  FOREIGN KEY (item_id) REFERENCES item (id)
);
CREATE TABLE task (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  created       TIMESTAMP           DEFAULT now(),
  name          VARCHAR             NOT NULL,
  due_date      TIMESTAMP           DEFAULT now(),
  is_assigned   BOOL                DEFAULT FALSE,
  status        VARCHAR             NOT NULL,
  priority      VARCHAR             NOT NULL,
  person_id     INTEGER,
  department_id INTEGER             NOT NULL,
  FOREIGN KEY (person_id) REFERENCES person (id),
  FOREIGN KEY (department_id) REFERENCES department (id)
--   ON DELETE CASCADE
);




CREATE TABLE person_roles(
  person_id     INTEGER             NOT NULL,
  role          VARCHAR,
  CONSTRAINT person_roles_idx UNIQUE (person_id, role),
  FOREIGN KEY (person_id) REFERENCES person (id)
--   ON DELETE CASCADE
);