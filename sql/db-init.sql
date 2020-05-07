use facultative;

INSERT INTO type(name)
VALUES ('admin'),('teacher'),('student');

INSERT INTO `user`(login, password, type_id, name, surname, contacts) 
VALUES
('admin1','1234', (SELECT id FROM type WHERE name = 'admin'), 'Andrew1','Lozychenko','095-86-00-271'),
('admin2','1234', (SELECT id FROM type WHERE name = 'admin'), 'Andrew2','Lozychenko','095-86-00-271'),
('admin3','1234', (SELECT id FROM type WHERE name = 'admin'), 'Andrew3','Lozychenko','095-86-00-271'),
('teacher1','1234', (SELECT id FROM type WHERE name = 'teacher'), 'Drew1','Lozychenko','095-86-00-271'),
('teacher2','1234', (SELECT id FROM type WHERE name = 'teacher'), 'Drew2','Lozychenko','095-86-00-271'),
('teacher3','1234', (SELECT id FROM type WHERE name = 'teacher'), 'Drew3','Lozychenko','095-86-00-271'),
('student1','1234', (SELECT id FROM type WHERE name = 'student'), 'Andy1','Lozychenko','095-86-00-271'),
('student2','1234', (SELECT id FROM type WHERE name = 'student'), 'Andy2','Lozychenko','095-86-00-271'),
('student3','1234', (SELECT id FROM type WHERE name = 'student'), 'Andy3','Lozychenko','095-86-00-271');