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
VALUES (1, 0, 'Админ', 'Админ', 'M', 17, 'admin@gmail.com', '200ceb26807d6bf99fd6f4f0d1ca54d4', 0,
        '2019-12-01 00:00:00'),
       (2, 1, 'Виталий', 'Морозов', 'M', 36, 'morozov@gmail.com', '54e25ae8633f344209e582f90f0574c5', 0, NOW()),
       (3, 1, 'Светлана', 'Демчук', 'F', 29, 'demchuk@gmail.com', '28c60845a864afeaa07e6909cf105c67', 0, NOW()),
       (4, 2, 'Екатерина', 'Веремеева', 'F', 23, 'veremeeva@gmail.com', 'b61006c0cd3e40c55f39f2be19379a30', 0, NOW()),
       (5, 2, 'Дмитрий', 'Маркевич', 'M', 46, 'markevich@gmail.com', 'f7f556640c262eec7face45fb18b47de', 1, NOW());

CREATE TABLE client
(
    id       BIGINT NOT NULL,
    discount DECIMAL(8, 5) DEFAULT 0,
    PRIMARY KEY (id)
);

INSERT INTO client (id, discount)
    VALUE (4, 0.00000),
    (5, 0.00000);

CREATE TABLE trainer
(
    id                   BIGINT        NOT NULL,
    education            VARCHAR(30)   NOT NULL DEFAULT 'master',
    cost_per_one_workout DECIMAL(6, 2) NOT NULL DEFAULT 10.0,
    PRIMARY KEY (id)
);

INSERT INTO trainer (id, education, cost_per_one_workout)
    VALUE (2, 'КМС', 30.0),
    (3, 'Мастер', 25.0);

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

INSERT INTO `order` (id, type_of_workout, number_of_workout, id_trainer, equipment, description, id_client, is_paid,
                     date_of_order)
    VALUE (1, 'power', 26, 2, 'Гантели', 'Умеренная тренировка', 4, 0, NOW()),
    (2, 'aerobic', 35, 3, 'Коврик', 'Легкая нагрузка', 4, 0, NOW()),
    (3, 'cardio', 21, 2, 'По желанию', 'Умеренная тренировка', 4, 0, NOW()),
    (4, 'power', 42, 2, 'Штанга', 'Усиленная тренировка', 5, 0, NOW());

CREATE TABLE workout
(
    id              BIGINT      NOT NULL,
    name_of_workout VARCHAR(40) NOT NULL,
    info_of_workout VARCHAR(40) NOT NULL,
    PRIMARY KEY (id)
);
