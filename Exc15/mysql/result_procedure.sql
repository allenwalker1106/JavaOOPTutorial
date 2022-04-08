DROP PROCEDURE IF EXISTS InsertResult;
DELIMITER $$
CREATE PROCEDURE InsertResult(
	IN studentID INT,
	IN averageGrade DOUBLE,
	IN semesterID INT
)
BEGIN
	INSERT INTO results(averageGrade,semesterID) VALUES (averageGrade,semesterID);
	SET @last_id_in_table1 = LAST_INSERT_ID();
	INSERT INTO student_results(studentID,resultID) VALUES (studentID,@last_id_in_table1);
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS DeleteResult;
DELIMITER $$
CREATE PROCEDURE DeleteResult(
        IN studentID INT,
        IN semesterID INT)
BEGIN
    DELETE r,sr FROM results r
    INNER JOIN student_results sr
    ON r.resultID = sr.resultID
    WHERE sr.studentID =studentID
    AND r.semesterID =semesterID;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS UpdateResult;
DELIMITER $$
CREATE PROCEDURE UpdateResult(
	IN studentID INT,
	IN averageGrade DOUBLE,
	IN semesterID INT
)
BEGIN
	UPDATE results r
	INNER JOIN student_results sr
	ON r.resultID = sr.resultID
	SET r.averageGrade = averageGrade
	WHERE r.semesterID = semesterID
	AND sr.studentID = studentID;
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS GetResultByStudentID;
DELIMITER $$
CREATE PROCEDURE GetResultByStudentID(IN studentID INT)
BEGIN
    SELECT sr.studentID,r.resultID,r.averageGrade,s.semesterID,s.semesterName,s.semesterDate
    FROM student_results sr
    INNER JOIN results r
    ON sr.resultID= r.resultID
    INNER JOIN semesters s
    ON r.semesterID = s.semesterID
    WHERE sr.studentID = studentID
    ORDER BY s.semesterDate DESC;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS GetAllResult;
DELIMITER $$
CREATE PROCEDURE GetAllResult()
BEGIN
    SELECT sr.studentID,r.resultID,r.averageGrade,s.semesterID,s.semesterName,s.semesterDate
    FROM student_results sr
    INNER JOIN results r
    ON sr.resultID= r.resultID
    INNER JOIN semesters s
    ON r.semesterID = s.semesterID
    ORDER BY sr.studentID ASC;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS GetResultBySemester;
DELIMITER $$
CREATE PROCEDURE GetResultBySemester(IN semesterID INT)
BEGIN
    SELECT sr.studentID,r.resultID,r.averageGrade,s.semesterID,s.semesterName,s.semesterDate
    FROM student_results sr
    INNER JOIN results r
    ON sr.resultID= r.resultID
    INNER JOIN semesters s
    ON r.semesterID = s.semesterID
    WHERE r.semesterID = semesterID
    ORDER BY sr.studentID ASC;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS GetRegularStudentResult;
DELIMITER $$
CREATE PROCEDURE GetRegularStudentResult()
BEGIN
    SELECT sr.studentID,r.resultID,r.averageGrade,s.semesterID,s.semesterName,s.semesterDate
    FROM student_results sr
    INNER JOIN results r
    ON sr.resultID= r.resultID
    INNER JOIN students s1
    ON sr.studentID = s1.studentID
    INNER JOIN regular_students rs
    ON rs.studentID = s1.studentID
    INNER JOIN semesters s
    ON r.semesterID = s.semesterID
    ORDER BY sr.studentID ASC;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS GetServiceStudentResult;
DELIMITER $$
CREATE PROCEDURE GetServiceStudentResult()
BEGIN
    SELECT sr.studentID,r.resultID,r.averageGrade,s.semesterID,s.semesterName,s.semesterDate
    FROM student_results sr
    INNER JOIN results r
    ON sr.resultID= r.resultID
    INNER JOIN students s1
    ON sr.studentID = s1.studentID
    INNER JOIN in_service_students iss
    ON iss.studentID = s1.studentID
    INNER JOIN semesters s
    ON r.semesterID = s.semesterID
    ORDER BY sr.studentID ASC;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS GetResultByDepartment;
DELIMITER $$
CREATE PROCEDURE GetResultByDepartment(IN departmentID VARCHAR(15))
BEGIN
    SELECT sr.studentID,r.resultID,r.averageGrade,s.semesterID,s.semesterName,s.semesterDate
    FROM student_results sr
    INNER JOIN results r
    ON sr.resultID= r.resultID
    INNER JOIN students s1
    ON sr.studentID = s1.studentID
    INNER JOIN semesters s
    ON r.semesterID = s.semesterID
    WHERE s1.departmentID =departmentID
    ORDER BY sr.studentID ASC;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS GetStudentResultBySemester;
DELIMITER $$
CREATE PROCEDURE GetStudentResultBySemester(
	IN studentID INT,
	IN semesterID INT)
BEGIN
    SELECT sr.studentID,r.resultID,r.averageGrade,s.semesterID,s.semesterName,s.semesterDate
    FROM student_results sr
    INNER JOIN results r
    ON sr.resultID= r.resultID
    INNER JOIN semesters s
    ON r.semesterID = s.semesterID
    WHERE sr.studentID =studentID
    AND r.semesterID =semesterID
    ORDER BY sr.studentID ASC;
END $$
DELIMITER ;