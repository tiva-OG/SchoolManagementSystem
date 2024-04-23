CREATE TABLE courses_students(
	course_code varchar(6) references courses(code),
	reg_number varchar(9) references students(reg_number),
	constraint cs_pkey primary key(course_code, reg_number)
);

INSERT INTO courses_students VALUES ('MEE204', 'A6/19/N98');
INSERT INTO courses_students VALUES ('MTH202', 'A6/19/N98');
INSERT INTO courses_students VALUES ('MEE208', 'A6/19/N98');
INSERT INTO courses_students VALUES ('PHY104', 'A6/19/N98');
INSERT INTO courses_students VALUES ('CHM101', 'A6/19/N98');
INSERT INTO courses_students VALUES ('MEE415', 'A4/64/C00');
INSERT INTO courses_students VALUES ('MEE407', 'A4/64/C00');
INSERT INTO courses_students VALUES ('EEE309', 'A4/64/C00');
INSERT INTO courses_students VALUES ('MEE208', 'A4/64/C00');
INSERT INTO courses_students VALUES ('MEE411', 'A4/64/C00');
INSERT INTO courses_students VALUES ('MEE405', 'A4/64/C00');
INSERT INTO courses_students VALUES ('MTH341', 'A4/64/C00');
INSERT INTO courses_students VALUES ('STA344', 'A4/64/C00');
INSERT INTO courses_students VALUES ('MTH202', 'A4/64/C00');
INSERT INTO courses_students VALUES ('MEE208', 'B1/29/F66');
INSERT INTO courses_students VALUES ('MEE204', 'B1/29/F66');
INSERT INTO courses_students VALUES ('EEE201', 'A3/32/T17');
INSERT INTO courses_students VALUES ('MEE207', 'A3/32/T17');
INSERT INTO courses_students VALUES ('MEE206', 'A3/32/T17');
INSERT INTO courses_students VALUES ('MTH202', 'A3/32/T17');
INSERT INTO courses_students VALUES ('MEE207', 'A6/19/N98');

