package Model;

import java.util.ArrayList;
import java.util.Date;

public class Experience extends Employee{
    private int i_experienceYear;
    private String str_major;

    public Experience(String str_id, String str_fullName, Date o_birthDay, String str_phoneNumber, String str_email, Position e_type, ArrayList<Certificate> c_certificate, int i_experienceYear, String str_major) {
        super(str_id, str_fullName, o_birthDay, str_phoneNumber, str_email, e_type, c_certificate);
        this.i_experienceYear = i_experienceYear;
        this.str_major = str_major;
    }

    public int getExperienceYear() {
        return i_experienceYear;
    }

    public void setExperienceYear(int i_experienceYear) {
        this.i_experienceYear = i_experienceYear;
    }

    public String getMajor() {
        return str_major;
    }

    public void setMajor(String str_major) {
        this.str_major = str_major;
    }

    @Override
    public String toString() {
        return "Experience{" + '\n' +
                '\t' + "str_id='" + str_id + "\'," + '\n' +
                '\t' + "str_fullName='" + str_fullName + "\'," + '\n' +
                '\t' + "o_birthDay=" + o_birthDay + '\n' +
                '\t' + "str_phoneNumber='" + str_phoneNumber + "\'," + '\n' +
                '\t' + "str_email='" + str_email + "\'," + '\n' +
                '\t' + "e_type=" + e_type + '\n' +
                '\t' + "c_certificate=" + c_certificates + '\n' +
                '\t' + "i_experienceYear=" + i_experienceYear + '\n' +
                '\t' + "str_major='" + str_major + "\'," + '\n' +
                "}\n";
    }
}
