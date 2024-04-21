
DROP TABLE IF EXISTS universities CASCADE;
CREATE TABLE universities
(
    id            INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name   VARCHAR(255),
    city   VARCHAR(255),
    country VARCHAR(255)
);

INSERT INTO universities (name, city, country) VALUES ('Harvard', 'Cambridge', 'USA');
INSERT INTO universities (name, city, country) VALUES ('LBS', 'London', 'England');

DROP TABLE IF EXISTS courses CASCADE ;
CREATE TABLE courses
(
    id            INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    course_name   VARCHAR(255) NOT NULL,
    study_year    INT NOT NULL,
    university_id INT NOT NULL REFERENCES universities(id)
);

INSERT INTO courses (course_name, study_year, university_id)
VALUES ('History', 2020, 1);
INSERT INTO courses (course_name, study_year, university_id)
VALUES ('Mathematics', 2020, 2);
INSERT INTO courses (course_name, study_year, university_id)
VALUES ('Chemistry', 2020, 2);
INSERT INTO courses (course_name, study_year, university_id)
VALUES ('Management', 2021, 1);
INSERT INTO courses (course_name, study_year, university_id)
VALUES ('Biology', 2021, 2);

DROP TABLE IF EXISTS students CASCADE ;
CREATE TABLE students
(
    id            INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name   VARCHAR(255) NOT NULL,
    second_name   VARCHAR(255) NOT NULL,
    age   INT,
    from_city VARCHAR(255),
    university_id INT NOT NULL REFERENCES universities(id)
);

INSERT INTO students (first_name, second_name, age, from_city, university_id)
VALUES ('John', 'Doe', 22, 'London', 1);
INSERT INTO students (first_name, second_name, age, from_city, university_id)
VALUES ('Andrew', 'Bow', 20, 'Birmingham', 1);
INSERT INTO students (first_name, second_name, age, from_city, university_id)
VALUES ('Stan', 'Ros', 18, 'New York', 1);
INSERT INTO students (first_name, second_name, age, from_city, university_id)
VALUES ('Kira', 'Naitly', 21, 'Manchester', 2);
INSERT INTO students (first_name, second_name, age, from_city, university_id)
VALUES ('Arnold', 'Pons', 20, 'Paris', 2);
INSERT INTO students (first_name, second_name, age, from_city, university_id)
VALUES ('Sara', 'Wals', 23, 'Prague', 2);
INSERT INTO students (first_name, second_name, age, from_city, university_id)
VALUES ('Kris', 'Bird', 19, 'Moscow', 2);

DROP TABLE IF EXISTS students_courses CASCADE ;
CREATE TABLE students_courses
(
    student_id INT NOT NULL REFERENCES students(id),
    course_id INT NOT NULL REFERENCES courses(id),
    CONSTRAINT course_student_id PRIMARY KEY (student_id,course_id)
);

INSERT INTO students_courses
VALUES (1,1),(2,1),(3,2),(4,3),(5,4),(6,5),(7,4);
