# --- !Ups

create table `ADDRESS` (`ID` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`ZIP_CODE` TEXT NOT NULL,`CITY` TEXT NOT NULL,`STREET` TEXT NOT NULL,`HOUSE_NUMBER` TEXT NOT NULL);

# --- !Downs

DROP TABLE IF EXISTS `ADDRESS`;