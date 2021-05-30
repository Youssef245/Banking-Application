CREATE TABLE `bankaccounts` (
  `BankAccountID` int NOT NULL AUTO_INCREMENT,
  `BACreationDate` date DEFAULT NULL,
  `BACurrentBalance` double DEFAULT NULL,
  `CustomerID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`BankAccountID`),
  KEY `CustomerID` (`CustomerID`),
  CONSTRAINT `bankaccounts_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `banktransactions` (
  `BankTransactionID` int NOT NULL AUTO_INCREMENT,
  `BTCreationDate` date DEFAULT NULL,
  `BTAmount` double DEFAULT NULL,
  `BTFromAccount` int NOT NULL,
  `BTTOAccount` int NOT NULL,
  PRIMARY KEY (`BankTransactionID`),
  KEY `BTFromAccount` (`BTFromAccount`),
  KEY `BTTOAccount` (`BTTOAccount`),
  CONSTRAINT `banktransactions_ibfk_1` FOREIGN KEY (`BTFromAccount`) REFERENCES `bankaccounts` (`BankAccountID`),
  CONSTRAINT `banktransactions_ibfk_2` FOREIGN KEY (`BTTOAccount`) REFERENCES `bankaccounts` (`BankAccountID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `customers` (
  `CustomerID` varchar(255) NOT NULL,
  `CustomerName` varchar(255) DEFAULT NULL,
  `CustomerAdderss` varchar(255) DEFAULT NULL,
  `CustomerMobile` varchar(20) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
