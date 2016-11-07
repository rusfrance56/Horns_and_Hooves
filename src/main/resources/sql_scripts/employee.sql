CREATE TABLE `employee` (
  `id`            INT(11)     NOT NULL AUTO_INCREMENT,
  `name`          VARCHAR(20) NOT NULL,
  `department_id` INT(11)     NOT NULL,
  `middle_name`   VARCHAR(255)         DEFAULT NULL,
  `sur_name`      VARCHAR(255)         DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `employee_id_seq` (`id`),
  KEY `department_fk` (`department_id`),
  CONSTRAINT `FKbejtwvg9bxus2mffsm3swj3u9` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8;

INSERT INTO horns_and_hooves.employee (id, name, department_id, middle_name, sur_name)
VALUES (1, 'Sergey', 1, 'Olegovitch', 'Egorov');
INSERT INTO horns_and_hooves.employee (id, name, department_id, middle_name, sur_name)
VALUES (2, 'Egor', 1, 'Pavlovitch', 'Mayorov');
INSERT INTO horns_and_hooves.employee (id, name, department_id, middle_name, sur_name)
VALUES (3, 'Oleg', 1, 'Alexandrovitch', 'Petrov');
INSERT INTO horns_and_hooves.employee (id, name, department_id, middle_name, sur_name)
VALUES (4, 'Oleg', 2, 'Petrovitch', 'Klykov');
INSERT INTO horns_and_hooves.employee (id, name, department_id, middle_name, sur_name)
VALUES (5, 'Pavel', 2, 'Olegovitch', 'Sobolev');
INSERT INTO horns_and_hooves.employee (id, name, department_id, middle_name, sur_name)
VALUES (6, 'Armen', 3, 'Vakhtangovitch', 'Gugarov');