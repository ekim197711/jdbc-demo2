DROP TABLE IF EXISTS spaceship;

CREATE TABLE
    IF NOT EXISTS spaceship (
        id     SERIAL PRIMARY KEY,
        captain       varchar(250) NOT NULL,
        fuel         integer NOT NULL,
        shape      varchar(100) NOT NULL
        );