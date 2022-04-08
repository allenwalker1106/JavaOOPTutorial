package Entity;

import java.util.ArrayList;
import java.util.Date;

public abstract class Student {
    protected int i_studentID;
    protected String str_name;
    protected Date o_birthDate;
    protected int i_admissionYear;
    protected double d_admissionGrade;
    protected String str_departmentID;
    protected ArrayList<Result> c_results;

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
        return "Student{" +
                "i_studentID=" + i_studentID +
                ", str_name='" + str_name + '\'' +
                ", o_birthDate=" + o_birthDate +
                ", i_admissionYear=" + i_admissionYear +
                ", d_admissionGrade=" + d_admissionGrade +
                ", str_departmentID='" + str_departmentID + '\'' +
                ", c_results=" + c_results +
                '}';
    }
}
