DROP TRIGGER IF EXISTS students_bd;
DELIMITER $$
CREATE TRIGGER students_bd BEFORE DELETE
ON students
FOR EACH ROW
BEGIN
    DELETE r,sr FROM results r
    INNER JOIN student_results sr
    ON r.resultID = sr.resultID
    WHERE sr.studentID =OLD.studentID;

    DELETE  FROM regular_students rs
    WHERE rs.studentID =OLD.studentID;
    DELETE  FROM in_service_students iss
    WHERE iss.studentID =OLD.studentID;

END $$
DELIMITER ;
