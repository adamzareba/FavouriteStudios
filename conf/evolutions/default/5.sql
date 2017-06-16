# --- !Ups

create table `DEPARTMENT` (`ID` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`NAME` TEXT NOT NULL,`COMPANY_ID` BIGINT NOT NULL);

alter table `DEPARTMENT` add constraint `FK_DEPARTMENT_COMPANY` foreign key(`COMPANY_ID`) references `COMPANY`(`ID`) on update NO ACTION on delete NO ACTION;
# --- !Downs

DROP TABLE IF EXISTS `DEPARTMENT`;