DROP TABLE IF EXISTS voters;
DROP TABLE IF EXISTS candidates;

CREATE TABLE candidates
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    first_name  varchar(50)           NOT NULL,
    last_name varchar(50) NOT NULL ,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO candidates(first_name, last_name)
VALUES ('Kazys','Volciunas');

INSERT INTO candidates(first_name, last_name)
VALUES ('Pranas','Nuzmauskas');

CREATE TABLE voters
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    age        INT                   NOT NULL,
    gender     VARCHAR(10)           NOT NULL,
    city       VARCHAR(20)           NOT NULL,
    date       VARCHAR(30)           NOT NULL,
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    updated_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    candidate_id BIGINT REFERENCES candidates(id) NOT NULL
);

INSERT INTO voters(age, gender, city, date,candidate_id)
VALUES (30, 'MALE', 'VILNIUS', '08-05-2021 13:03:13',1);

INSERT INTO voters(age, gender, city, date,candidate_id)
VALUES (20, 'FEMALE', 'KAUNAS', '04-05-2021 10:03:13',1);


INSERT INTO voters(age, gender, city, date,candidate_id)
VALUES (23, 'FEMALE', 'VILNIUS', '05-05-2021 11:23:23',2);


