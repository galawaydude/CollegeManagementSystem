CREATE TABLE Student (
    name VARCHAR(255),
    rollNo VARCHAR(50) PRIMARY KEY,
    section VARCHAR(50),
    emailAddress VARCHAR(255),
    phoneNo VARCHAR(20),
    dateOfBirth VARCHAR(15)
);

CREATE TABLE Teacher (
    name VARCHAR(255),
    rollNo VARCHAR(50) PRIMARY KEY,
    emailAddress VARCHAR(255),
    phoneNo VARCHAR(20),
    dateOfBirth VARCHAR(15)
);

CREATE TABLE Users (
    username VARCHAR(50) PRIMARY KEY NOT NULL,
    password VARCHAR(50) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE Section (
    name VARCHAR(50) UNIQUE
);

CREATE TABLE Course (
    name VARCHAR(255) UNIQUE
);

CREATE TABLE Teacher_Section (
    id SERIAL PRIMARY KEY,
    teacher_rollNo VARCHAR(50) REFERENCES Teacher(rollNo),
    section_name VARCHAR(50) REFERENCES Section(name)
);

CREATE TABLE Student_Course (
    id SERIAL PRIMARY KEY,
    student_rollNo VARCHAR(50) REFERENCES Student(rollNo),
    course_name VARCHAR(50) REFERENCES Course(name)
);

CREATE TABLE Teacher_Course (
    id SERIAL PRIMARY KEY,
    teacher_rollNo VARCHAR(50) REFERENCES Teacher(rollNo),
    course_name VARCHAR(50) REFERENCES Course(name)
);

