DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS customer_order;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE GLOBAL_SEQ START 100000;

CREATE TABLE users (
  id            INTEGER             DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
  created       TIMESTAMP           DEFAULT now(),
  name          VARCHAR(50)         NOT NULL,
  surname       VARCHAR(50)         NOT NULL,
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
  user_id     INTEGER,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE TABLE item (
  id            INTEGER             DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
  created       TIMESTAMP           DEFAULT now(),
  name          VARCHAR(50)         NOT NULL,
  description   VARCHAR,
  image_url     VARCHAR,
  price          NUMERIC(10,2)
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
  user_id     INTEGER,
  department    VARCHAR(20)         NOT NULL,
  description   VARCHAR,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL
);
CREATE TABLE user_roles(
  user_id     INTEGER             NOT NULL,
  role          VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);