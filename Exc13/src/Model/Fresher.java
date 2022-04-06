package Model;

import java.util.ArrayList;
import java.util.Date;

public class Fresher extends Employee{
    private Date o_graduationDate;
    private int i_graduateRank;
    private String str_graduateSchool;

    public Fresher(String str_id, String str_fullName, Date o_birthDay, String str_phoneNumber, String str_email, Position e_type, ArrayList<Certificate> c_certificate, Date o_graduationDate, int i_graduateRank, String str_graduateSchool) {
        super(str_id, str_fullName, o_birthDay, str_phoneNumber, str_email, e_type, c_certificate);
        this.o_graduationDate = o_graduationDate;
        this.i_graduateRank = i_graduateRank;
        this.str_graduateSchool = str_graduateSchool;
    }

    public Date getGraduationDate() {
        return o_graduationDate;
    }

    public void setGraduationDate(Date o_graduationDate) {
        this.o_graduationDate = o_graduationDate;
    }

    public int getGraduateRank() {
        return i_graduateRank;
    }

    public void setGraduateRank(int i_graduateRank) {
        this.i_graduateRank = i_graduateRank;
    }

    public String getGraduateSchool() {
        return str_graduateSchool;
    }

    public void setGraduateSchool(String str_graduateSchool) {
        this.str_graduateSchool = str_graduateSchool;
    }

    @Override
    public String toString() {
        return "Fresher{" + '\n' +
                '\t' + "str_id='" + str_id + "\'," + '\n' +
                '\t' + "str_fullName='" + str_fullName + "\'," + '\n' +
                '\t' + "o_birthDay=" + o_birthDay + '\n' +
                '\t' + "str_phoneNumber='" + str_phoneNumber + "\'," + '\n' +
                '\t' + "str_email='" + str_email + "\'," + '\n' +
                '\t' + "e_type=" + e_type + '\n' +
                '\t' + "c_certificate=" + c_certificates + '\n' +
                '\t' + "o_graduationDate=" + o_graduationDate + '\n' +
                '\t' + "str_graduateRank='" + i_graduateRank + "\'," + '\n' +
                '\t' + "str_graduateSchool='" + str_graduateSchool + "\'," + '\n' +
                "}\n";
    }
}
