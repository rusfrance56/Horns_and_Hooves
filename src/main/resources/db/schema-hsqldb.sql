DROP TABLE order_furniture IF EXISTS;
DROP TABLE employee_roles IF EXISTS;
DROP TABLE employee IF EXISTS;
DROP TABLE department IF EXISTS;
DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE department (
  id   INTEGER    GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name  VARCHAR(255)         NOT NULL
);
CREATE TABLE employee (
  id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name          VARCHAR(255)         NOT NULL,
  department_id VARCHAR(255)         NOT NULL,
  middle_name   VARCHAR(255)         DEFAULT NULL,
  sur_name      VARCHAR(255)         NOT NULL,
  FOREIGN KEY (department_id) REFERENCES department (id) ON DELETE CASCADE
);
CREATE TABLE order_furniture (
  id            INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name          VARCHAR(255)         NOT NULL,
  date          TIMESTAMP            DEFAULT now(),
  is_assigned   BOOLEAN              DEFAULT FALSE,
  employee_id   INTEGER              DEFAULT NULL,
  department_id INTEGER              NOT NULL,
  FOREIGN KEY (employee_id) REFERENCES employee (id),
  FOREIGN KEY (department_id) REFERENCES department (id) ON DELETE CASCADE
);
CREATE TABLE employee_roles
(
  employee_id INTEGER NOT NULL,
  role        VARCHAR(255),
  FOREIGN KEY (employee_id) REFERENCES employee (id) ON DELETE CASCADE
);
