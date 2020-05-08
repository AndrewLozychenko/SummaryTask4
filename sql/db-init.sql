use facultative;

INSERT INTO type(name)
VALUES ('admin'),('teacher'),('student');

INSERT INTO `user`(login, password, type_id, name, surname, contacts) 
VALUES
('admin1','b1b3773a05c0ed0176787a4f1574ff0075f7521e', (SELECT id FROM type WHERE name = 'admin'), 'Andrew','Lozychenko','095-86-00-271'),
('admin2','b1b3773a05c0ed0176787a4f1574ff0075f7521e', (SELECT id FROM type WHERE name = 'admin'), 'Ivan','Petrov','095-86-00-271'),
('admin3','b1b3773a05c0ed0176787a4f1574ff0075f7521e', (SELECT id FROM type WHERE name = 'admin'), 'Petr','Ivanov','095-86-00-271');