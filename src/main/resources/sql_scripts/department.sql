CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `department_id_seq` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO horns_and_hooves.department (id, name) VALUES (1, 'cushioned');
INSERT INTO horns_and_hooves.department (id, name) VALUES (2, 'storage');
INSERT INTO horns_and_hooves.department (id, name) VALUES (3, 'office');