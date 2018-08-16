DROP TABLE IF EXISTS order_furniture;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE GLOBAL_SEQ START WITH 100000;

CREATE TABLE department (
  id            INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
  name          VARCHAR(255)         NOT NULL
);
CREATE TABLE employee (
  id            INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
  name          VARCHAR(255)         NOT NULL,
  department_id INTEGER              NOT NULL,
  middle_name   VARCHAR(255)         DEFAULT NULL,
  sur_name      VARCHAR(255)         NOT NULL,
  FOREIGN KEY (department_id) REFERENCES department (id) ON DELETE CASCADE
);
CREATE TABLE order_furniture (
  id            INTEGER DEFAULT GLOBAL_SEQ.nextval PRIMARY KEY,
  name          VARCHAR(255)         NOT NULL,
  date          TIMESTAMP            DEFAULT now(),
  is_assigned   BOOLEAN              DEFAULT FALSE,
  employee_id   INTEGER              DEFAULT NULL,
  department_id INTEGER              NOT NULL,
  FOREIGN KEY (employee_id) REFERENCES employee (id),
  FOREIGN KEY (department_id) REFERENCES department (id) ON DELETE CASCADE
);
