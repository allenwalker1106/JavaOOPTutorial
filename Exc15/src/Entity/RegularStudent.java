package Entity;

import java.util.ArrayList;
import java.util.Date;

public class RegularStudent extends Student{
    public RegularStudent(int i_studentID, String str_name, Date o_birthDate, int i_admissionYear, double d_admissionGrade, ArrayList<Result> c_results) {
        super(i_studentID, str_name, o_birthDate, i_admissionYear, d_admissionGrade, c_results);
    }

    public RegularStudent(int i_studentID, String str_name, Date o_birthDate, int i_admissionYear, double d_admissionGrade) {
        super(i_studentID, str_name, o_birthDate, i_admissionYear, d_admissionGrade);
    }

}
