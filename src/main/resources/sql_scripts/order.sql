CREATE TABLE `order_furniture` (
  `id`            INT(11)     NOT NULL AUTO_INCREMENT,
  `name`          VARCHAR(30) NOT NULL,
  `date`          DATETIME    NOT NULL,
  `order_status`  VARCHAR(10)          DEFAULT NULL,
  `employee_id`   INT(11)              DEFAULT NULL,
  `department_id` INT(11)     NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_id_seq` (`id`),
  KEY `FKp3lmskay2makeut2ui1mjh1fr` (`department_id`),
  KEY `FK5xix38en1lwphlngvlu10fti4` (`employee_id`),
  CONSTRAINT `FK5xix38en1lwphlngvlu10fti4` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`),
  CONSTRAINT `FKp3lmskay2makeut2ui1mjh1fr` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = utf8;

INSERT INTO horns_and_hooves.order_furniture (id, name, date, order_status, employee_id, department_id)
VALUES (7, 'leather sofa', '2017-10-11 15:36:38', 'assigned', 1, 1);
INSERT INTO horns_and_hooves.order_furniture (id, name, date, order_status, employee_id, department_id)
VALUES (8, 'leather chair', '2017-10-22 17:59:38', 'assigned', 1, 1);
INSERT INTO horns_and_hooves.order_furniture (id, name, date, order_status, employee_id, department_id)
VALUES (9, 'leather chair', '2017-10-12 12:00:00', 'assigned', 3, 1);
INSERT INTO horns_and_hooves.order_furniture (id, name, date, order_status, employee_id, department_id)
VALUES (10, 'bookshelf', '2017-11-08 12:30:00', 'assigned', 4, 2);
INSERT INTO horns_and_hooves.order_furniture (id, name, date, order_status, employee_id, department_id)
VALUES (11, 'rocking chair', '2017-11-12 14:38:00', 'assigned', 6, 3);