# --- Sample dataset

# --- !Ups

insert into `COMPANY`(`ID`, `NAME`) values (1, 'Pepsi');
insert into `COMPANY`(`ID`, `NAME`) values (2, 'Coca Cola');
insert into `COMPANY`(`ID`, `NAME`) values (3, 'Sprite');

insert into `CAR`(`ID`, `REGISTRATION_NUMBER`, `COMPANY_ID`) values (1, 'XYZ10ABC', 1);
insert into `CAR`(`ID`, `REGISTRATION_NUMBER`, `COMPANY_ID`) values (2, 'XYZ11ABC', 1);
insert into `CAR`(`ID`, `REGISTRATION_NUMBER`, `COMPANY_ID`) values (3, 'XYZ12ABC', 1);
insert into `CAR`(`ID`, `REGISTRATION_NUMBER`, `COMPANY_ID`) values (4, 'XYZ13ABC', 2);

insert into `ADDRESS`(`ID`, `HOUSE_NUMBER`, `STREET`, `ZIP_CODE`, `CITY`) VALUES (1, 1, 'Street X', '12-341', 'Los Angeles');
insert into `ADDRESS`(`ID`, `HOUSE_NUMBER`, `STREET`, `ZIP_CODE`, `CITY`) VALUES (2, 2, 'Street Y', '12-342', 'New York');
insert into `ADDRESS`(`ID`, `HOUSE_NUMBER`, `STREET`, `ZIP_CODE`, `CITY`) VALUES (3, 3, 'Street Z', '12-343', 'Washington');
insert into `ADDRESS`(`ID`, `HOUSE_NUMBER`, `STREET`, `ZIP_CODE`, `CITY`) VALUES (4, 4, 'Street XX', '12-344', 'Seattle');
insert into `ADDRESS`(`ID`, `HOUSE_NUMBER`, `STREET`, `ZIP_CODE`, `CITY`) VALUES (5, 5, 'Street YY', '12-345', 'Houston');
insert into `ADDRESS`(`ID`, `HOUSE_NUMBER`, `STREET`, `ZIP_CODE`, `CITY`) VALUES (6, 6, 'Street ZZ', '12-346', 'Boston');
insert into `ADDRESS`(`ID`, `HOUSE_NUMBER`, `STREET`, `ZIP_CODE`, `CITY`) VALUES (7, 7, 'Street XXX', '12-347', 'Denver');

insert into `DEPARTMENT`(`ID`, `NAME`, `COMPANY_ID`) VALUES (1, 'Sales & Marketing', 1);
insert into `DEPARTMENT`(`ID`, `NAME`, `COMPANY_ID`) VALUES (2, 'Research & Development', 1);
insert into `DEPARTMENT`(`ID`, `NAME`, `COMPANY_ID`) VALUES (3, 'Administration', 1);
insert into `DEPARTMENT`(`ID`, `NAME`, `COMPANY_ID`) VALUES (4, 'Human Resources', 2);
insert into `DEPARTMENT`(`ID`, `NAME`, `COMPANY_ID`) VALUES (5, 'Sales & Marketing', 3);

insert into `EMPLOYEE`(`ID`, `NAME`, `SURNAME`, `ADDRESS_ID`, `DEPARTMENT_ID`) VALUES (1, 'John', 'William', 1, 1);
insert into `EMPLOYEE`(`ID`, `NAME`, `SURNAME`, `ADDRESS_ID`, `DEPARTMENT_ID`) VALUES (2, 'Robert', 'James', 2, 2);
insert into `EMPLOYEE`(`ID`, `NAME`, `SURNAME`, `ADDRESS_ID`, `DEPARTMENT_ID`) VALUES (3, 'Donald', 'Tyler', 3, 3);

# --- !Downs

delete from `EMPLOYEE`;
delete from `DEPARTMENT`;
delete from `ADDRESS`;
delete from `CAR`;
delete from `COMPANY`;