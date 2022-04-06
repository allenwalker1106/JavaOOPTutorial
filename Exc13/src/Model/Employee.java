package Model;

import java.util.ArrayList;
import java.util.Date;

public abstract class Employee {
    protected String str_id;
    protected String str_fullName;
    protected Date o_birthDay;
    protected String str_phoneNumber;
    protected String str_email;
    protected Position e_type;
    protected ArrayList<Certificate> c_certificates;
    protected static int i_employeeCount;

    public Employee(String str_id, String str_fullName, Date o_birthDay, String str_phoneNumber, String str_email, Position e_type, ArrayList<Certificate> c_certificates) {
        this.str_id = str_id;
        this.str_fullName = str_fullName;
        this.o_birthDay = o_birthDay;
        this.str_phoneNumber = str_phoneNumber;
        this.str_email = str_email;
        this.e_type = e_type;
        this.c_certificates = c_certificates;
    }

    public enum Position {
        EXPERIENCE,
        FRESHER,
        INTERN,
        INVALID
    }


    public String getId() {
        return str_id;
    }

    public static void increaseEmployeeNumber(){
        Employee.i_employeeCount++;
    }

    public static void reduceEmployeeNumber(){Employee.i_employeeCount--;}

    public void setId(String str_id) {
        this.str_id = str_id;
    }

    public String getFullName() {
        return str_fullName;
    }

    public void setFullName(String str_fullName) {
        this.str_fullName = str_fullName;
    }

    public Date getBirthDay() {
        return o_birthDay;
    }

    public void setBirthDay(Date o_birthDay) {
        this.o_birthDay = o_birthDay;
    }

    public String getPhoneNumber() {
        return str_phoneNumber;
    }

    public void setPhoneNumber(String str_phoneNumber) {
        this.str_phoneNumber = str_phoneNumber;
    }

    public String getEmail() {
        return str_email;
    }

    public void setEmail(String str_email) {
        this.str_email = str_email;
    }

    public Position getType() {
        return e_type;
    }

    public void setType(Position e_type) {
        this.e_type = e_type;
    }

    public static int getEmployeeCount() {
        return i_employeeCount;
    }

    public static void setEmployeeCount(int i_employeeCount) {
        Employee.i_employeeCount = i_employeeCount;
    }

    public ArrayList<Certificate> getCertificate() {
        return c_certificates;
    }

    public void setCertificate(ArrayList<Certificate> c_certificate) {
        this.c_certificates = c_certificate;
    }
    
    @Override
    public String toString() {
        return "Employee{" + '\n' +
                '\t' + "str_id='" + str_id + "\'," + '\n' +
                '\t' + "str_fullName='" + str_fullName + "\'," + '\n' +
                '\t' + "o_birthDay=" + o_birthDay + '\n' +
                '\t' + "str_phoneNumber='" + str_phoneNumber + "\'," + '\n' +
                '\t' + "str_email='" + str_email + "\'," + '\n' +
                '\t' + "e_type=" + e_type + '\n' +
                '\t' + "c_certificate=" + c_certificates + '\n' +
                "}\n";
    }
}
