DROP TABLE IF EXISTS real_spaceship;
DROP TABLE IF EXISTS real_captain;
CREATE TABLE
    IF NOT EXISTS real_captain (
                                     id     SERIAL PRIMARY KEY,
                                     name varchar (200),
                                     experience integer ,
                                     no_of_crashes  integer
    );

CREATE TABLE
    IF NOT EXISTS real_spaceship (
        id     SERIAL PRIMARY KEY,
        captain_id       int NOT NULL,
        fuel         integer NOT NULL,
        shape      varchar(255),
        CONSTRAINT fk_captain_id FOREIGN KEY (captain_id) REFERENCES real_captain (id)
        );
