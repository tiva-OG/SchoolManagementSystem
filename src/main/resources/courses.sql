CREATE TABLE courses (
    code VARCHAR(6) NOT NULL,
    title text NOT NULL,
    level integer NOT NULL,
    credit_units integer NOT NULL,
    CONSTRAINT courses_pkey PRIMARY KEY (code)
);


INSERT INTO courses (code, title, level, credit_units) VALUES ('CHM101', 'Physical Chemistry', 100, 3);
INSERT INTO courses (code, title, level, credit_units) VALUES ('GST103', 'Nigerian People and Culture', 100, 2);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MTH101', 'Algebra & Trigonometry', 100, 3);
INSERT INTO courses (code, title, level, credit_units) VALUES ('CHM105', 'Inorganic Chemistry', 100, 3);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MTH103', 'Calculus', 100, 3);
INSERT INTO courses (code, title, level, credit_units) VALUES ('PHY104', 'Practical Physics', 100, 2);
INSERT INTO courses (code, title, level, credit_units) VALUES ('GST102', 'Philosophy and Logic', 100, 2);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MEE206', 'Thermosciences', 200, 2);
INSERT INTO courses (code, title, level, credit_units) VALUES ('CVE201', 'Strength of Materials', 200, 2);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MEE204', 'Engineering Drawing', 200, 2);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MEE208', 'Materials Science', 200, 2);
INSERT INTO courses (code, title, level, credit_units) VALUES ('GST223', 'Entrepreneurship Studies', 200, 2);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MTH202', 'Differential Equations', 200, 3);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MEE207', 'Manufacturing Technology', 200, 1);
INSERT INTO courses (code, title, level, credit_units) VALUES ('CVE204', 'Engineering Hydromechanics', 200, 3);
INSERT INTO courses (code, title, level, credit_units) VALUES ('EEE201', 'Electrical Circuit Theory', 200, 2);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MEE309', 'Engineering Fluid Mechanics', 300, 2);
INSERT INTO courses (code, title, level, credit_units) VALUES ('EEE313', 'Programming for Engineers', 300, 2);
INSERT INTO courses (code, title, level, credit_units) VALUES ('EEE309', 'Electrical Machines', 300, 3);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MTH341', 'Numerical Analysis', 300, 3);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MEE312', 'Strength of Materials', 300, 2);
INSERT INTO courses (code, title, level, credit_units) VALUES ('STA344', 'Engineering Statistics', 300, 2);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MEE313', 'Engineering Metallurgy', 300, 2);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MEE304', 'Dynamics of Machinery', 300, 2);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MEE407', 'Machine Design', 400, 3);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MEE415', 'Law and Management', 400, 1);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MEE413', 'AutoCAD Drawing', 400, 2);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MEE405', 'Automatic Control', 400, 2);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MEE411', 'Production Technology', 400, 3);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MEE510', 'Fluid Dynamics', 500, 3);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MEE508', 'Theory of Vibrations', 500, 3);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MEE509', 'Thermodynamics', 500, 3);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MEE512', 'Maintenance Engineering', 500, 3);
INSERT INTO courses (code, title, level, credit_units) VALUES ('MEE520', 'Refrigeration and Air Conditioning', 500, 2);