# --- !Ups

create table `CAR` (`ID` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`REGISTRATION_NUMBER` TEXT NOT NULL,`COMPANY_ID` BIGINT NOT NULL);

alter table `CAR` add constraint `FK_CAR_COMPANY` foreign key(`COMPANY_ID`) references `COMPANY`(`ID`) on update NO ACTION on delete NO ACTION;
# --- !Downs

DROP TABLE IF EXISTS `CAR`;