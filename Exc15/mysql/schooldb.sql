USE schooldb;
DROP TABLE IF EXISTS regular_students;
DROP TABLE IF EXISTS in_service_students;
DROP TABLE IF EXISTS student_results;
DROP TABLE IF EXISTS results;
DROP TABLE IF EXISTS semesters;
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS departments;




CREATE TABLE departments(
    departmentID VARCHAR(15) NOT NULL PRIMARY KEY,
    departmentName VARCHAR(50) NOT NULL
);


CREATE TABLE students(
    studentID INT NOT NULL  AUTO_INCREMENT,
    studentName VARCHAR(50) NOT NULL,
    studentDateOfBirth DATE NOT NULL,
    studentAdmissionYear YEAR NOT NULL,
    studentAdmissionGrade DOUBLE NOT NULL,
    departmentID VARCHAR(15) NOT NULL,
    PRIMARY KEY (studentID),
    CONSTRAINT FK_students_departments FOREIGN KEY (departmentID) REFERENCES departments(departmentID)

);

CREATE TABLE regular_students(
    studentID INT NOT NULL,
    CONSTRAINT FK_regularStudents_students FOREIGN KEY (studentID) REFERENCES students(studentID)
);

CREATE TABLE in_service_students(
    studentID INT NOT NULL,
    trainingSite VARCHAR(20) NOT NULL,
    CONSTRAINT FK_inServiceStudents_students FOREIGN KEY (studentID) REFERENCES students(studentID)
);

CREATE TABLE semesters(
    semesterID INT NOT NULL AUTO_INCREMENT,
    semesterName VARCHAR(50) NOT NULL,
    semesterDate DATE NOT NULL,
    PRIMARY KEY (semesterID)
);

CREATE TABLE results(
    resultID INT NOT NULL AUTO_INCREMENT,
    averageGrade DOUBLE NOT NULL,
    semesterID INT NOT NULL,
    PRIMARY KEY (resultID),
    CONSTRAINT FK_results_semesters FOREIGN KEY (semesterID) REFERENCES semesters(semesterID)
);

CREATE TABLE student_results(
    studentID INT NOT NULL,
    resultID INT NOT NULL,
    CONSTRAINT FK_studentResults_students FOREIGN KEY (studentID) REFERENCES students(studentID),
    CONSTRAINT FK_studentResults_results FOREIGN KEY (resultID) REFERENCES results(resultID)
);








