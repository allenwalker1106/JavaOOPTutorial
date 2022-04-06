package Entity;

import java.util.Date;

public class Result {
    private int i_studentID;
    private int i_resultID;
    private double d_averageGrade;
    private int i_semesterID;
    private String str_semesterName;
    private Date o_semesterDate;

    public Result(int i_studentID, double d_averageGrade, int i_semesterID) {
        this.i_studentID = i_studentID;
        this.d_averageGrade = d_averageGrade;
        this.i_semesterID = i_semesterID;
    }

    public Result(int i_studentID, int i_resultID, double d_averageGrade, int i_semesterID, String str_semesterName, Date o_semesterDate) {
        this.i_studentID = i_studentID;
        this.i_resultID = i_resultID;
        this.d_averageGrade = d_averageGrade;
        this.i_semesterID = i_semesterID;
        this.str_semesterName = str_semesterName;
        this.o_semesterDate = o_semesterDate;
    }

    public int getStudentID() {
        return i_studentID;
    }

    public void setStudentID(int i_studentID) {
        this.i_studentID = i_studentID;
    }

    public int getResultID() {
        return i_resultID;
    }

    public void setResultID(int i_resultID) {
        this.i_resultID = i_resultID;
    }

    public int getSemesterID() {
        return i_semesterID;
    }

    public void setSemesterID(int i_semesterID) {
        this.i_semesterID = i_semesterID;
    }

    public String getSemesterName() {
        return str_semesterName;
    }

    public void setSemesterName(String str_semesterName) {
        this.str_semesterName = str_semesterName;
    }

    public Date getSemesterDate() {
        return o_semesterDate;
    }

    public void setSemesterDate(Date o_semesterDate) {
        this.o_semesterDate = o_semesterDate;
    }

    public double getAverageGrade() {
        return d_averageGrade;
    }

    public void setAverageGrade(double d_averageGrade) {
        this.d_averageGrade = d_averageGrade;
    }

    @Override
    public String toString() {
        return "Result{" + '\n' +
                '\t' + "i_studentID=" + i_studentID + '\n' +
                '\t' + "d_averageGrade=" + d_averageGrade + '\n' +
                '\t' + "i_semesterID=" + i_semesterID + '\n' +
                '\t' + "str_semesterName='" + str_semesterName + "\'," + '\n' +
                '\t' + "o_semesterDate=" + o_semesterDate + '\n' +
                "}\n";
    }
}
