CREATE TABLE students (
     id BIGSERIAL PRIMARY KEY,
    student_number VARCHAR(20) UNIQUE NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    phone VARCHAR(30),
    status VARCHAR(30) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP NOT NULL DEFAULT NOW()

);

CREATE TABLE instructors (
id BIGSERIAL PRIMARY KEY,
staff_number VARCHAR(20) UNIQUE NOT NULL,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
email VARCHAR(150) UNIQUE NOT NULL,
created_at TIMESTAMP NOT NULL DEFAULT NOW()

);

CREATE TABLE courses (
id BIGSERIAL PRIMARY KEY,
course_code VARCHAR(20) UNIQUE NOT NULL,
course_name VARCHAR(200) NOT NULL,
credits INT NOT NULL,
 created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE course_offerings
(
     id BIGSERIAL PRIMARY KEY,
    course_id BIGINT NOT NULL REFERENCES courses(id),
    instructor_id BIGINT NOT NULL REFERENCES instructors(id),
    semester VARCHAR(20) NOT NULL,
    year INT NOT NULL,
    capacity INT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    UNIQUE(course_id, semester, year)

);

CREATE TABLE enrollments(

id BIGSERIAL PRIMARY KEY,
student_id BIGINT NOT NULL REFERENCES students(id),
offering_id BIGINT NOT NULL REFERENCES course_offerings(id),
enrolled_at TIMESTAMP NOT NULL DEFAULT NOW(),
status VARCHAR(30) NOT NULL DEFAULT 'ENROLLED',
UNIQUE(student_id, offering_id)

);