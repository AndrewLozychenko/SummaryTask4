use facultative;

INSERT INTO type(name)
VALUES ('admin'),('teacher'),('student');

INSERT INTO `user`(login, password, type_id, name, surname, contacts) 
VALUES
('admin1','7110eda4d09e062aa5e4a390b0a572ac0d2c0220', (SELECT id FROM type WHERE name = 'admin'), 'Andrew1','Lozychenko','095-86-00-271'),
('admin2','7110eda4d09e062aa5e4a390b0a572ac0d2c0220', (SELECT id FROM type WHERE name = 'admin'), 'Andrew2','Lozychenko','095-86-00-271'),
('admin3','7110eda4d09e062aa5e4a390b0a572ac0d2c0220', (SELECT id FROM type WHERE name = 'admin'), 'Andrew3','Lozychenko','095-86-00-271'),
('teacher1','7110eda4d09e062aa5e4a390b0a572ac0d2c0220', (SELECT id FROM type WHERE name = 'teacher'), 'Drew1','Lozychenko','095-86-00-271'),
('teacher2','7110eda4d09e062aa5e4a390b0a572ac0d2c0220', (SELECT id FROM type WHERE name = 'teacher'), 'Drew2','Lozychenko','095-86-00-271'),
('teacher3','7110eda4d09e062aa5e4a390b0a572ac0d2c0220', (SELECT id FROM type WHERE name = 'teacher'), 'Drew3','Lozychenko','095-86-00-271'),
('student1','7110eda4d09e062aa5e4a390b0a572ac0d2c0220', (SELECT id FROM type WHERE name = 'student'), 'Andy1','Lozychenko','095-86-00-271'),
('student2','7110eda4d09e062aa5e4a390b0a572ac0d2c0220', (SELECT id FROM type WHERE name = 'student'), 'Andy2','Lozychenko','095-86-00-271'),
('student3','7110eda4d09e062aa5e4a390b0a572ac0d2c0220', (SELECT id FROM type WHERE name = 'student'), 'Andy3','Lozychenko','095-86-00-271');