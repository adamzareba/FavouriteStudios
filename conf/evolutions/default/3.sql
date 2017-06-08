# --- !Ups

CREATE TABLE car (
  ID BIGINT NOT NULL PRIMARY KEY,
  REGISTRATION_NUMBER VARCHAR(255),
  COMPANY_ID BIGINT
);


# --- !Downs

DROP TABLE IF EXISTS car;