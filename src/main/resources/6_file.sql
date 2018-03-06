CREATE TABLE `file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comp_id` int(11) NOT NULL,
  `file_name` varchar(50) DEFAULT NULL,
  `file` mediumblob,
  PRIMARY KEY (`id`),
  KEY `comp_id` (`comp_id`),
  CONSTRAINT `file_ibfk_1` FOREIGN KEY (`comp_id`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
