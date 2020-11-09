DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS customer_order;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS person_roles;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS person;
DROP SEQUENCE IF EXISTS GLOBAL_SEQ;

CREATE SEQUENCE GLOBAL_SEQ START 100000;

CREATE TABLE person (
  id            INTEGER             DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
  created       TIMESTAMP           DEFAULT now(),
  name          VARCHAR(50)         NOT NULL,
  surname       VARCHAR(50)         NOT NULL,
  middle_name   VARCHAR(50),
  address       VARCHAR             NOT NULL,
  phone         VARCHAR(20)         NOT NULL,
  email         VARCHAR(50)         NOT NULL,
  department    VARCHAR(20),
  description   VARCHAR
);
CREATE TABLE customer_order (
  id            INTEGER             DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
  created       TIMESTAMP           DEFAULT now(),
  name          VARCHAR(50)         NOT NULL,
  due_date      TIMESTAMP,
  status        VARCHAR(10)         NOT NULL,
  description   VARCHAR,
  person_id     INTEGER,
  FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE CASCADE
);
CREATE TABLE item (
  id            INTEGER             DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
  created       TIMESTAMP           DEFAULT now(),
  name          VARCHAR(50)         NOT NULL,
  description   VARCHAR,
  image_url     VARCHAR,
  cost          NUMERIC(10,2)
);
CREATE TABLE order_items (
  customer_order_id     INTEGER             NOT NULL,
  item_id               INTEGER,
  CONSTRAINT order_items_idx UNIQUE (customer_order_id, item_id),
  FOREIGN KEY (customer_order_id) REFERENCES customer_order (id) ON DELETE CASCADE,
  FOREIGN KEY (item_id) REFERENCES item (id) ON DELETE RESTRICT
);
CREATE TABLE task (
  id            INTEGER             DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
  created       TIMESTAMP           DEFAULT now(),
  name          VARCHAR(50)         NOT NULL,
  due_date      TIMESTAMP           DEFAULT now(),
  status        VARCHAR(10)         NOT NULL,
  priority      VARCHAR(10)         NOT NULL,
  person_id     INTEGER,
  department    VARCHAR(20)         NOT NULL,
  description   VARCHAR,
  FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE SET NULL
);
CREATE TABLE person_roles(
  person_id     INTEGER             NOT NULL,
  role          VARCHAR,
  CONSTRAINT person_roles_idx UNIQUE (person_id, role),
  FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE CASCADE
);