CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comp_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `homephone` varchar(12) DEFAULT NULL,
  `mobilephone` varchar(12) DEFAULT NULL,
  `workphone` varchar(12) NOT NULL,
  `address` varchar(50) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `bankdata` varchar(30) DEFAULT NULL,
  `position` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `comp_id` (`comp_id`),
  CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`comp_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
