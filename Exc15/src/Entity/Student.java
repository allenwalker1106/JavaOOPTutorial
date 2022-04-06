package Entity;

import java.util.ArrayList;
import java.util.Date;

public abstract class Student {
    private int i_studentID;
    private String str_name;
    private Date o_birthDate;
    private int i_admissionYear;
    private double d_admissionGrade;
    private String str_departmentID;

    private ArrayList<Result> c_results;

    public Student(int i_studentID, String str_name, Date o_birthDate, int i_admissionYear, double d_admissionGrade, String str_departmentID) {
        this.i_studentID = i_studentID;
        this.str_name = str_name;
        this.o_birthDate = o_birthDate;
        this.i_admissionYear = i_admissionYear;
        this.d_admissionGrade = d_admissionGrade;
        this.str_departmentID = str_departmentID;
        this.c_results = new ArrayList<>();
    }

    public Student(int i_studentID, String str_name, Date o_birthDate, int i_admissionYear, double d_admissionGrade, String str_departmentID, ArrayList<Result> c_results) {
        this.i_studentID = i_studentID;
        this.str_name = str_name;
        this.o_birthDate = o_birthDate;
        this.i_admissionYear = i_admissionYear;
        this.d_admissionGrade = d_admissionGrade;
        this.str_departmentID = str_departmentID;
        this.c_results = c_results;
    }

    public int getStudentID() {
        return i_studentID;
    }

    public void setStudentID(int i_studentID) {
        this.i_studentID = i_studentID;
    }

    public String getName() {
        return str_name;
    }

    public void setName(String str_name) {
        this.str_name = str_name;
    }

    public Date getBirthDate() {
        return o_birthDate;
    }

    public void setBirthDate(Date o_birthDate) {
        this.o_birthDate = o_birthDate;
    }

    public int getAdmissionYear() {
        return i_admissionYear;
    }

    public void setAdmissionYear(int i_admissionYear) {
        this.i_admissionYear = i_admissionYear;
    }

    public double getAdmissionGrade() {
        return d_admissionGrade;
    }

    public void setAdmissionGrade(double d_admissionGrade) {
        this.d_admissionGrade = d_admissionGrade;
    }

    public ArrayList<Result> getResults() {
        return c_results;
    }

    public void setResults(ArrayList<Result> c_results) {
        this.c_results = c_results;
    }

    public String getDepartmentID() {
        return str_departmentID;
    }

    public void setDepartmentID(String str_departmentID) {
        this.str_departmentID = str_departmentID;
    }

    @Override
    public String toString() {
        return "Student{" + '\n' +
                '\t' + "i_studentID=" + i_studentID + '\n' +
                '\t' + "str_name='" + str_name + "\'," + '\n' +
                '\t' + "o_birthDate=" + o_birthDate + '\n' +
                '\t' + "i_admissionYear=" + i_admissionYear + '\n' +
                '\t' + "d_admissionGrade=" + d_admissionGrade + '\n' +
                '\t' + "str_departmentID='" + str_departmentID + "\'," + '\n' +
                '\t' + "c_results=" + c_results + '\n' +
                "}\n";
    }
}
