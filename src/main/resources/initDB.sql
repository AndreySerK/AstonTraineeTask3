CREATE TABLE IF NOT EXISTS universities
(
    id            INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name   VARCHAR(255),
    city   VARCHAR(255),
    country VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS courses
(
    id            INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    course_name   VARCHAR(255) NOT NULL,
    study_year    INT NOT NULL,
    university_id INT NOT NULL REFERENCES universities(id)
);

CREATE TABLE IF NOT EXISTS students
(
    id            INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name   VARCHAR(255) NOT NULL,
    second_name   VARCHAR(255) NOT NULL,
    age   INT,
    from_city VARCHAR(255),
    university_id INT NOT NULL REFERENCES universities(id)
);

CREATE TABLE IF NOT EXISTS students_courses
(
    student_id INT NOT NULL REFERENCES students(id),
    course_id INT NOT NULL REFERENCES courses(id),
    CONSTRAINT course_student_id PRIMARY KEY (student_id,course_id)
);