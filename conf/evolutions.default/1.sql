# --- !Ups
CREATE TABLE favouriteStudio (
	  USER_ID BIGINT NOT NULL,
    STUDIO_ID BIGINT NOT NULL,
    PRIMARY KEY idx_user_studio (USER_ID, STUDIO_ID),
    INDEX idx_user(USER_ID)
);

# --- !Downs
DROP TABLE IF EXISTS favouriteStudio;

