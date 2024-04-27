
INSERT INTO universities (name, city, country) VALUES ('Harvard', 'Cambridge', 'USA');
INSERT INTO universities (name, city, country) VALUES ('LBS', 'London', 'England');


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


INSERT INTO courses_students
VALUES (1,1),(1,2),(2,3),(3,4),(4,5),(4,7),(5,6);
