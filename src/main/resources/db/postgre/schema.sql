DROP TABLE IF EXISTS orders_items;
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS customer_orders;
DROP TABLE IF EXISTS item_images;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS users_roles;
DROP TABLE IF EXISTS roles_permissions;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS permissions;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id            SERIAL              PRIMARY KEY,
  created       TIMESTAMP           DEFAULT now(),
  updated       TIMESTAMP           DEFAULT now(),
  logon_name    VARCHAR(30)         NOT NULL UNIQUE,
  password      VARCHAR(80)         NOT NULL,
  name          VARCHAR(50)         NOT NULL,
  surname       VARCHAR(50)         NOT NULL,
  address       VARCHAR             NOT NULL,
  phone         VARCHAR(20)         NOT NULL,
  email         VARCHAR(50)         NOT NULL,
  department    VARCHAR(20),
  status        VARCHAR(20)         DEFAULT 'ACTIVE',
  description   VARCHAR
);
/*CREATE TABLE user_logons (
  id            SERIAL              PRIMARY KEY,
  created       TIMESTAMP           DEFAULT now(),
  logon_name    VARCHAR(30)         NOT NULL UNIQUE,
  password      VARCHAR(80)         NOT NULL,
  user_id       INTEGER,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);*/
CREATE TABLE customer_orders (
  id            SERIAL              PRIMARY KEY,
  created       TIMESTAMP           DEFAULT now(),
  updated       TIMESTAMP           DEFAULT now(),
  name          VARCHAR(50)         NOT NULL,
  due_date      TIMESTAMP,
  status        VARCHAR(10)         NOT NULL,
  description   VARCHAR,
  user_id       INTEGER,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE TABLE items (
  id            SERIAL              PRIMARY KEY,
  created       TIMESTAMP           DEFAULT now(),
  updated       TIMESTAMP           DEFAULT now(),
  name          VARCHAR(50)         NOT NULL,
  description   VARCHAR,
  department    VARCHAR(20)         NOT NULL,
  cost          NUMERIC(10,2)
);
CREATE TABLE item_images (
  name          VARCHAR(300)         NOT NULL,
  item_id       INTEGER,
  CONSTRAINT item_images_idx UNIQUE (name, item_id),
  FOREIGN KEY (item_id) REFERENCES items (id) ON DELETE CASCADE
);
CREATE TABLE orders_items (
  customer_order_id     INTEGER             NOT NULL,
  item_id               INTEGER,
--   CONSTRAINT orders_items_idx UNIQUE (customer_order_id, item_id),
  FOREIGN KEY (customer_order_id) REFERENCES customer_orders (id) ON DELETE CASCADE,
  FOREIGN KEY (item_id) REFERENCES items (id) ON DELETE RESTRICT
);
CREATE TABLE tasks (
  id            SERIAL              PRIMARY KEY,
  created       TIMESTAMP           DEFAULT now(),
  updated       TIMESTAMP           DEFAULT now(),
  name          VARCHAR(50)         NOT NULL,
  due_date      TIMESTAMP           DEFAULT now(),
  status        VARCHAR(10)         NOT NULL,
  priority      VARCHAR(10)         NOT NULL,
  user_id       INTEGER,
  item_id       INTEGER,
  order_id      INTEGER,
  department    VARCHAR(20)         NOT NULL,
  description   VARCHAR,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE SET NULL,
  FOREIGN KEY (item_id) REFERENCES items (id),
  FOREIGN KEY (order_id) REFERENCES customer_orders (id)
);
CREATE TABLE roles(
  id            SERIAL              PRIMARY KEY,
  created       TIMESTAMP           DEFAULT now(),
  updated       TIMESTAMP           DEFAULT now(),
  name          VARCHAR(50)         NOT NULL,
  description   VARCHAR
);
CREATE TABLE permissions(
  id            SERIAL              PRIMARY KEY,
  created       TIMESTAMP           DEFAULT now(),
  updated       TIMESTAMP           DEFAULT now(),
  name          VARCHAR(50)         NOT NULL,
  description   VARCHAR
);
CREATE TABLE roles_permissions(
  role_id           INTEGER             NOT NULL,
  permission_id     INTEGER             NOT NULL,
  CONSTRAINT roles_permissions_idx UNIQUE (role_id, permission_id),
  FOREIGN KEY (role_id) REFERENCES roles (id),
  FOREIGN KEY (permission_id) REFERENCES permissions (id)
);
CREATE TABLE users_roles(
  user_id     INTEGER             NOT NULL,
  role_id     INTEGER             NOT NULL,
  CONSTRAINT users_roles_idx UNIQUE (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (role_id) REFERENCES roles (id)
);