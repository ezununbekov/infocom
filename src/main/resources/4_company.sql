CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ownership` int(11) NOT NULL,
  `legalform` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `managername` varchar(50) NOT NULL,
  `fax` varchar(12) DEFAULT NULL,
  `phone` varchar(12) NOT NULL,
  `webpage` varchar(30) DEFAULT NULL,
  `license` varchar(30) NOT NULL,
  `licensedate` date NOT NULL,
  `certificate` varchar(30) NOT NULL,
  `certdate` date NOT NULL,
  `address` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `license` (`license`),
  KEY `ownership` (`ownership`),
  KEY `legalform` (`legalform`),
  CONSTRAINT `company_ibfk_1` FOREIGN KEY (`ownership`) REFERENCES `ownership` (`id`),
  CONSTRAINT `company_ibfk_2` FOREIGN KEY (`legalform`) REFERENCES `legalform` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;