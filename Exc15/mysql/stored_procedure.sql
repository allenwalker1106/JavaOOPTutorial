DROP PROCEDURE IF EXISTS InsertRegularStudent;
DELIMITER $$
CREATE PROCEDURE InsertRegularStudent(
	IN studentID INT ,
    	IN studentName VARCHAR(50),
    	IN studentDateOfBirth DATE ,
    	IN studentAdmissionYear YEAR ,
    	IN studentAdmissionGrade DOUBLE,
    	IN departmentID VARCHAR(15))
BEGIN
START TRANSACTION;
	INSERT INTO students(studentID,studentName,studentDateOfBirth,studentAdmissionYear,studentAdmissionGrade,departmentID)
	VALUES (studentID,studentName,studentDateOfBirth,studentAdmissionYear,studentAdmissionGrade,departmentID);
	INSERT INTO regular_students(studentID)
	VALUES (studentID);
 COMMIT;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS DeleteRegularStudent;
DELIMITER $$
CREATE PROCEDURE DeleteRegularStudent(
	IN studentID INT )
BEGIN
START TRANSACTION;
    DELETE r FROM results r
    INNER JOIN student_results sr
    ON r.resultID = sr.resultID
    WHERE sr.studentID = studentID;
    DELETE FROM student_results WHERE student_results.studentID =studentID;
    DELETE FROM regular_students WHERE regular_students.studentID = studentID;
    DELETE FROM students WHERE students.studentID = studentID;
 COMMIT;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS UpdateRegularStudent;
DELIMITER $$
CREATE PROCEDURE UpdateRegularStudent(
	IN studentID INT ,
    	IN studentName VARCHAR(50),
    	IN studentDateOfBirth DATE ,
    	IN studentAdmissionYear YEAR ,
    	IN studentAdmissionGrade DOUBLE,
    	IN departmentID VARCHAR(15))
BEGIN
START TRANSACTION;
    UPDATE students
    SET studentName=studentName, studentDateOfBirth=studentDateOfBirth, studentAdmissionYear=studentAdmissionYear,studentAdmissionGrade=studentAdmissionGrade, departmentID=departmentID
    WHERE students.studentID = studentID;
 COMMIT;
END$$
DELIMITER ;



DROP PROCEDURE IF EXISTS InsertInServiceStudent;
DELIMITER $$
CREATE PROCEDURE InsertInServiceStudent(
	IN studentID INT ,
    	IN studentName VARCHAR(50),
    	IN studentDateOfBirth DATE ,
    	IN studentAdmissionYear YEAR ,
    	IN studentAdmissionGrade DOUBLE,
    	IN departmentID VARCHAR(15),
    	IN trainingSite VARCHAR(20))
BEGIN
START TRANSACTION;
	INSERT INTO students(studentID,studentName,studentDateOfBirth,studentAdmissionYear,studentAdmissionGrade,departmentID)
	VALUES (studentID,studentName,studentDateOfBirth,studentAdmissionYear,studentAdmissionGrade,departmentID);
	INSERT INTO in_service_students(studentID,trainingSite)
	VALUES (studentID,trainingSite);
 COMMIT;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS DeleteInServiceStudent;
DELIMITER $$
CREATE PROCEDURE DeleteInServiceStudent(
	IN studentID INT )
BEGIN
START TRANSACTION;
    DELETE r FROM results r
    INNER JOIN student_results sr
    ON r.resultID = sr.resultID
    WHERE sr.studentID = studentID;
    DELETE FROM student_results WHERE student_results.studentID =studentID;
    DELETE FROM in_service_students WHERE in_service_students.studentID = studentID;
    DELETE FROM students WHERE students.studentID = studentID;
 COMMIT;
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS UpdateInServiceStudent;
DELIMITER $$
CREATE PROCEDURE UpdateInServiceStudent(
	IN studentID INT ,
    IN studentName VARCHAR(50),
    IN studentDateOfBirth DATE ,
    IN studentAdmissionYear YEAR ,
    IN studentAdmissionGrade DOUBLE,
    IN departmentID VARCHAR(15),
    IN trainingSite VARCHAR(20))
BEGIN
START TRANSACTION;
    UPDATE students
    SET studentName=studentName, studentDateOfBirth=studentDateOfBirth, studentAdmissionYear=studentAdmissionYear,studentAdmissionGrade=studentAdmissionGrade, departmentID=departmentID
    WHERE students.studentID = studentID;
    UPDATE in_service_students
    SET  trainingSite=trainingSite
    WHERE in_service_students.studentID = studentID;
 COMMIT;
END$$
DELIMITER ;


DROP PROCEDURE IF EXISTS GetDepartmentMaxAdmissionGrade;
DELIMITER $$
CREATE PROCEDURE GetDepartmentMaxAdmissionGrade()
BEGIN
	SELECT d.departmentID,MAX(studentAdmissionGrade)
	FROM students s
	INNER JOIN departments d ON d.departmentID = s.departmentID
	GROUP BY departmentID;
END $$
DELIMITER ;



DROP PROCEDURE IF EXISTS GetStudentBySemesterGrade;
DELIMITER $$
CREATE PROCEDURE GetStudentBySemesterGrade(IN averageGrade DOUBLE)
BEGIN
SELECT sr.studentID,st.studentName,st.departmentID,r.resultID,r.averageGrade,Max(s.semesterDate),s.semesterName
FROM results r
INNER JOIN semesters s ON s.semesterID = r.semesterID
INNER JOIN student_results sr ON sr.resultID = r.resultID
INNER JOIN students st ON st.studentID = sr.studentID
WHERE r.averageGrade> averageGrade
GROUP BY sr.studentID,r.resultID;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS GetDepartmentStudentNumber;
DELIMITER $$
CREATE PROCEDURE GetDepartmentStudentNumber()
BEGIN
SELECT  d.departmentID,COUNT(*) FROM students s
INNER JOIN departments d
ON s.departmentID = d.departmentID
GROUP BY d.departmentID;
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS GetDepartmentRegularStudentNumber;
DELIMITER $$
CREATE PROCEDURE GetDepartmentRegularStudentNumber()
BEGIN
SELECT  d.departmentID,COUNT(*) FROM students s
INNER JOIN departments d
ON s.departmentID = d.departmentID
INNER JOIN regular_students rs
ON s.studentID = rs.studentID
GROUP BY d.departmentID;
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS GetStudentByID;
DELIMITER $$
CREATE PROCEDURE GetStudentByID(IN studentID INT)
BEGIN
SELECT s.studentID,s.studentName,s.studentDateOfBirth,s.studentAdmissionYear,s.studentAdmissionGrade,iss.trainingSite,d.departmentID,d.departmentName
FROM students s
LEFT JOIN regular_students rs
ON s.studentID = rs.studentID
LEFT JOIN in_service_students iss
ON s.studentID = iss.studentID
INNER JOIN departments d
ON s.departmentID =d.departmentID
WHERE s.studentID = studentID;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS GetStudents;
DELIMITER $$
CREATE PROCEDURE GetStudents()
BEGIN
SELECT s.studentID,s.studentName,s.studentDateOfBirth,s.studentAdmissionYear,s.studentAdmissionGrade,iss.trainingSite,d.departmentID,d.departmentName
FROM students s
LEFT JOIN regular_students rs
ON s.studentID = rs.studentID
LEFT JOIN in_service_students iss
ON s.studentID = iss.studentID
INNER JOIN departments d
ON s.departmentID =d.departmentID;
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS GetRegularStudents;
DELIMITER $$
CREATE PROCEDURE GetRegularStudents()
BEGIN
SELECT s.studentID,s.studentName,s.studentDateOfBirth,s.studentAdmissionYear,s.studentAdmissionGrade,d.departmentID,d.departmentName
FROM regular_students rs
INNER JOIN students s
ON rs.studentID = s.studentID
INNER JOIN departments d
ON s.departmentID = d.departmentID;
END $$
DELIMITER ;



DROP PROCEDURE IF EXISTS GetInServiceStudent;
DELIMITER $$
CREATE PROCEDURE GetInServiceStudent()
BEGIN
SELECT s.studentID,s.studentName,s.studentDateOfBirth,s.studentAdmissionYear,s.studentAdmissionGrade,iss.trainingSite,d.departmentID,d.departmentName
FROM in_service_students iss
INNER JOIN students s
ON iss.studentID = s.studentID
INNER JOIN departments d
ON s.departmentID = d.departmentID;
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

DROP PROCEDURE IF EXISTS GetResults;
DELIMITER $$
CREATE PROCEDURE GetResults()
BEGIN
SELECT sr.studentID,r.resultID,r.averageGrade,s.semesterID,s.semesterName,s.semesterDate
FROM student_results sr
INNER JOIN results r
ON sr.resultID= r.resultID
INNER JOIN semesters s
ON r.semesterID = s.semesterID
ORDER BY sr.studentID ASC ;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS GetResultBySemesterID;
DELIMITER $$
CREATE PROCEDURE GetResultBySemesterID(IN semesterID INT)
BEGIN
SELECT sr.studentID,r.resultID,r.averageGrade,s.semesterID,s.semesterName,s.semesterDate
FROM student_results sr
INNER JOIN results r
ON sr.resultID= r.resultID
INNER JOIN semesters s
ON r.semesterID = s.semesterID
WHERE r.semesterID = semesterID
ORDER BY sr.studentID ASC ;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS GetResultByStudentIDSemesterID;
DELIMITER $$
CREATE PROCEDURE GetResultByStudentIDSemesterID(
	IN studentID INT,
	IN semesterID INT
)
BEGIN
SELECT sr.studentID,r.resultID,r.averageGrade,s.semesterID,s.semesterName,s.semesterDate
FROM student_results sr
INNER JOIN results r
ON sr.resultID= r.resultID
INNER JOIN semesters s
ON r.semesterID = s.semesterID
WHERE r.semesterID = semesterID
AND
sr.studentID = studentID;
END $$
DELIMITER ;

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

DROP PROCEDURE IF EXISTS deleteResult;
DELIMITER $$
CREATE PROCEDURE deleteResult(IN resultID INT)
BEGIN
DELETE FROM student_results
WHERE resultID=resultID;
DELETE FROM results
WHERE resultID=resultID;
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS GetServiceStudentByTrainingSite;
DELIMITER $$
CREATE PROCEDURE GetServiceStudentByTrainingSite(
	IN trainingSite VARCHAR(20)
)
BEGIN
	SELECT s.studentID,s.studentName,s.studentDateOfBirth,s.studentAdmissionYear,s.studentAdmissionGrade,iss.trainingSite,d.departmentID,d.departmentName FROM in_service_students iss
	INNER JOIN students s ON iss.studentID = s.studentID
	INNER JOIN departments d ON d.departmentID = s.departmentID
	WHERE iss.trainingSite = trainingSite ;
END $$
DELIMITER ;