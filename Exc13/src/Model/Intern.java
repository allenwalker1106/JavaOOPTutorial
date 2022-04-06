package Model;

import java.util.ArrayList;
import java.util.Date;

public class Intern extends Employee{
    private String str_major;
    private int i_semester;
    private String str_universityName;

    public Intern(String str_id, String str_fullName, Date o_birthDay, String str_phoneNumber, String str_email, Position e_type, ArrayList<Certificate> c_certificate, String str_major, int i_semester, String str_universityName) {
        super(str_id, str_fullName, o_birthDay, str_phoneNumber, str_email, e_type, c_certificate);
        this.str_major = str_major;
        this.i_semester = i_semester;
        this.str_universityName = str_universityName;
    }

    public String getMajor() {
        return str_major;
    }

    public void setMajor(String str_major) {
        this.str_major = str_major;
    }

    public int getSemester() {
        return i_semester;
    }

    public void setSemester(int i_semester) {
        this.i_semester = i_semester;
    }

    public String getUniversityName() {
        return str_universityName;
    }

    public void setUniversityName(String str_universityName) {
        this.str_universityName = str_universityName;
    }

    @Override
    public String toString() {
        return "Intern{" + '\n' +
                '\t' + "str_id='" + str_id + "\'," + '\n' +
                '\t' + "str_fullName='" + str_fullName + "\'," + '\n' +
                '\t' + "o_birthDay=" + o_birthDay + '\n' +
                '\t' + "str_phoneNumber='" + str_phoneNumber + "\'," + '\n' +
                '\t' + "str_email='" + str_email + "\'," + '\n' +
                '\t' + "e_type=" + e_type + '\n' +
                '\t' + "c_certificate=" + c_certificates + '\n' +
                '\t' + "str_major='" + str_major + "\'," + '\n' +
                '\t' + "i_semester=" + i_semester + '\n' +
                '\t' + "str_universityName='" + str_universityName + "\'," + '\n' +
                "}\n";
    }
}
