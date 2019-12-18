CREATE DATABASE fitness_center_db;

CREATE TABLE role_legend
(
    role_num TINYINT     NOT NULL,
    role     VARCHAR(10) NOT NULL,
    PRIMARY KEY (role_num)
);

INSERT INTO role_legend (role_num, role)
VALUES (0, 'admin'),
       (1, 'trainer'),
       (2, 'client');

CREATE TABLE user
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    role_num             TINYINT               NOT NULL,
    name                 VARCHAR(20)           NOT NULL,
    surname              VARCHAR(30)           NOT NULL,
    gender               VARCHAR(6)            NOT NULL,
    year_old             TINYINT               NOT NULL,
    email                VARCHAR(40)           NOT NULL,
    password             VARCHAR(40)           NOT NULL,
    blocked              TINYINT               NOT NULL DEFAULT 0,
    date_of_registration DATETIME              NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id),
    FOREIGN KEY (role_num) REFERENCES role_legend (role_num),
    UNIQUE (email)
);

INSERT INTO user (id, role_num, name, surname, gender, year_old, email, password, blocked, date_of_registration)
VALUES (1, 0, 'Админ', 'Админ', 'M', 17, 'admin@gmail.com', '200ceb26807d6bf99fd6f4f0d1ca54d4', 0, NOW());

CREATE TABLE client
(
    id       BIGINT NOT NULL,
    discount DECIMAL(8, 5) DEFAULT 0,
    PRIMARY KEY (id)
);

CREATE TABLE trainer
(
    id                   BIGINT        NOT NULL,
    education            VARCHAR(30)   NOT NULL DEFAULT 'master',
    cost_per_one_workout DECIMAL(6, 2) NOT NULL DEFAULT 10.0,
    PRIMARY KEY (id)
);

CREATE TABLE `order`
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    type_of_workout   VARCHAR(20)           NOT NULL,
    number_of_workout INT                   NULL,
    id_trainer        BIGINT                NOT NULL,
    equipment         VARCHAR(40)           NOT NULL DEFAULT 'water',
    description       VARCHAR(100)          NOT NULL DEFAULT 'for health',
    id_client         BIGINT                NOT NULL,
    is_paid           TINYINT               NOT NULL DEFAULT 0,
    date_of_order     DATETIME              NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id),
    FOREIGN KEY (id_trainer) REFERENCES trainer (id),
    FOREIGN KEY (id_client) REFERENCES client (id)
);

CREATE TABLE workout
(
    id              BIGINT      NOT NULL,
    name_of_workout VARCHAR(40) NOT NULL,
    info_of_workout VARCHAR(40) NOT NULL,
    PRIMARY KEY (id)
);