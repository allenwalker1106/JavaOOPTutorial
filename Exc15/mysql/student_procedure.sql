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



DROP PROCEDURE IF EXISTS DeleteStudent;
DELIMITER $$
CREATE PROCEDURE DeleteStudent( IN studentID INT )
BEGIN
    DELETE FROM students s WHERE s.studentID = studentID;
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


DROP PROCEDURE IF EXISTS GetAllStudent;
DELIMITER $$
CREATE PROCEDURE GetAllStudent()
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

DROP PROCEDURE IF EXISTS GetRegularStudent;
DELIMITER $$
CREATE PROCEDURE GetRegularStudent()
BEGIN
    SELECT s.studentID,s.studentName,s.studentDateOfBirth,s.studentAdmissionYear,s.studentAdmissionGrade,d.departmentID,d.departmentName
    FROM regular_students rs
    INNER JOIN students s
    ON rs.studentID = s.studentID
    INNER JOIN departments d
    ON s.departmentID = d.departmentID;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS GetServiceStudent;
DELIMITER $$
CREATE PROCEDURE GetServiceStudent()
BEGIN
    SELECT s.studentID,s.studentName,s.studentDateOfBirth,s.studentAdmissionYear,s.studentAdmissionGrade,iss.trainingSite,d.departmentID,d.departmentName
    FROM in_service_students iss
    INNER JOIN students s
    ON iss.studentID = s.studentID
    INNER JOIN departments d
    ON s.departmentID = d.departmentID;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS GetStudentByDepartment;
DELIMITER $$
CREATE PROCEDURE GetStudentByDepartment(IN departmentID VARCHAR(15))
BEGIN
    SELECT s.studentID,s.studentName,s.studentDateOfBirth,s.studentAdmissionYear,s.studentAdmissionGrade,iss.trainingSite,d.departmentID,d.departmentName
    FROM students s
    LEFT JOIN regular_students rs
    ON s.studentID = rs.studentID
    LEFT JOIN in_service_students iss
    ON s.studentID = iss.studentID
    INNER JOIN departments d
    ON s.departmentID =d.departmentID
    WHERE s.departmentID = departmentID;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS GetStudentByTrainingSite;
DELIMITER $$
CREATE PROCEDURE GetStudentByTrainingSite(IN trainingSite VARCHAR(20))
BEGIN
    SELECT s.studentID,s.studentName,s.studentDateOfBirth,s.studentAdmissionYear,s.studentAdmissionGrade,iss.trainingSite,d.departmentID,d.departmentName
    FROM students s
    LEFT JOIN regular_students rs
    ON s.studentID = rs.studentID
    LEFT JOIN in_service_students iss
    ON s.studentID = iss.studentID
    INNER JOIN departments d
    ON s.departmentID =d.departmentID
    WHERE iss.trainingSite = trainingSite;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS GetDepartmentRegularCount;
DELIMITER $$
CREATE PROCEDURE GetDepartmentRegularCount()
BEGIN
SELECT  d.departmentID,COUNT(*) as counter FROM students s
INNER JOIN departments d
ON s.departmentID = d.departmentID
GROUP BY d.departmentID;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS GetDepartmentTopAdmissionGrade;
DELIMITER $$
CREATE PROCEDURE GetDepartmentTopAdmissionGrade()
BEGIN
SELECT s.departmentID,Max(s.studentAdmissionGrade) as counter FROM students  s
GROUP BY s.departmentID;
END $$
DELIMITER ;


DROP PROCEDURE IF EXISTS GetStudentByDepartmentAdmissionGrade;
DELIMITER $$
CREATE PROCEDURE GetStudentByDepartmentAdmissionGrade(
IN departmentID VARCHAR(15),
IN studentAdmissionGrade DOUBLE)
BEGIN
    SELECT s.studentID,s.studentName,s.studentDateOfBirth,s.studentAdmissionYear,s.studentAdmissionGrade,iss.trainingSite,d.departmentID,d.departmentName
    FROM students s
    LEFT JOIN regular_students rs
    ON s.studentID = rs.studentID
    LEFT JOIN in_service_students iss
    ON s.studentID = iss.studentID
    INNER JOIN departments d
    ON s.departmentID =d.departmentID
    WHERE s.departmentID = departmentID
    AND s.studentAdmissionGrade=studentAdmissionGrade;
END $$
DELIMITER ;