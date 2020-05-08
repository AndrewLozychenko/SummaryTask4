USE facultative;

DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS type;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS topic;
DROP TABLE IF EXISTS journal;
DROP TABLE IF EXISTS student;

CREATE TABLE `user`
(
    id       integer AUTO_INCREMENT PRIMARY KEY,
    login    varchar(30) UNIQUE NOT NULL,
    password text               NOT NULL,
    type_id  integer            NOT NULL REFERENCES type (id),
    blocked  boolean            NOT NULL DEFAULT FALSE,
    name     varchar(30)        NOT NULL,
    surname  varchar(30)        NOT NULL,
    contacts text
);

CREATE TABLE type
(
    id   integer AUTO_INCREMENT PRIMARY KEY,
    name varchar(50) UNIQUE NOT NULL
);

CREATE TABLE course
(
    id          integer AUTO_INCREMENT PRIMARY KEY,
    name        varchar(200) NOT NULL,
    teacher_id  integer      NOT NULL REFERENCES `user` (id),
    begin       varchar(10)  NOT NULL,
    end         varchar(10)  NOT NULL,
    topic_id    integer      NOT NULL REFERENCES topic (id),
    party_limit integer      NOT NULL
);

CREATE TABLE topic
(
    id   integer AUTO_INCREMENT PRIMARY KEY,
    name varchar(200) UNIQUE NOT NULL
);

CREATE TABLE journal
(
    id         integer AUTO_INCREMENT PRIMARY KEY,
    student_id integer NOT NULL
        REFERENCES student (id)
            ON DELETE CASCADE,
    mark       integer NOT NULL
);

CREATE TABLE student
(
    id        integer AUTO_INCREMENT PRIMARY KEY,
    user_id   integer NOT NULL
        REFERENCES `user` (id)
            ON DELETE CASCADE,
    course_id integer NOT NULL
        REFERENCES course (id)
            ON DELETE CASCADE
);
