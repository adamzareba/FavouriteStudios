# --- !Ups

create table `OFFICE` (`ID` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,`NAME` TEXT NOT NULL,`ADDRESS_ID` BIGINT NOT NULL,`DEPARTMENT_ID` BIGINT NOT NULL);

alter table `OFFICE` add constraint `FK_OFFICE_ADDRESS` foreign key(`ADDRESS_ID`) references `ADDRESS`(`ID`) on update NO ACTION on delete NO ACTION;
alter table `OFFICE` add constraint `FK_OFFICE_DEPARTMENT` foreign key(`DEPARTMENT_ID`) references `DEPARTMENT`(`ID`) on update RESTRICT on delete NO ACTION;
# --- !Downs

DROP TABLE IF EXISTS `OFFICE`;