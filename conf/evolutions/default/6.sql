# --- !Ups

create table `EMPLOYEE` (`ID` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`NAME` TEXT NOT NULL,`SURNAME` TEXT NOT NULL,`ADDRESS_ID` BIGINT NOT NULL,`DEPARTMENT_ID` BIGINT NOT NULL);

alter table `EMPLOYEE` add constraint `FK_EMPLOYEE_ADDRESS` foreign key(`ADDRESS_ID`) references `ADDRESS`(`ID`) on update NO ACTION on delete NO ACTION;
alter table `EMPLOYEE` add constraint `FK_EMPLOYEE_DEPARTMENT` foreign key(`DEPARTMENT_ID`) references `DEPARTMENT`(`ID`) on update NO ACTION on delete NO ACTION;
# --- !Downs

DROP TABLE IF EXISTS `EMPLOYEE`;