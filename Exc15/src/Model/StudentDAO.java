package Model;

import Entity.RegularStudent;
import Entity.ServiceStudent;
import Entity.Student;

import java.sql.*;
import java.util.HashMap;

public class StudentDAO {
    public Student getStudent(int i_studentID){
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            String str_query="{CALL GetStudentByID(?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setInt(1,i_studentID);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                return toStudent(o_result);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //dev
    public boolean insertStudent(Student o_student){
        if(o_student instanceof RegularStudent){

        }
        else if(o_student instanceof ServiceStudent){

        }
        return true;
    }

    public HashMap<Integer,Student> getStudents(){
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        HashMap<Integer,Student> c_students = new HashMap<>();
        try{
            String str_query="{CALL GetStudents()}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                Student o_student = toStudent(o_result);
                c_students.put(o_student.getStudentID(),o_student);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return c_students;
    }

    public HashMap<Integer,Student> getRegularStudents(){
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        HashMap<Integer,Student> c_students = new HashMap<>();
        try{
            String str_query="{CALL GetRegularStudents()}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                Student o_student = toRegularStudent(o_result);
                c_students.put(o_student.getStudentID(),o_student);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return c_students;
    }

    public HashMap<Integer,Student> getServiceStudent(){
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        HashMap<Integer,Student> c_students = new HashMap<>();
        try{
            String str_query="{CALL GetInServiceStudent()}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                Student o_student = toServiceStudent(o_result);
                c_students.put(o_student.getStudentID(),o_student);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return c_students;
    }

    private Student toStudent(ResultSet o_result){
        try {
            int i_resultStudentID = o_result.getInt("studentID");
            String str_resultStudentName = o_result.getString("studentName");
            java.util.Date o_resultDateOfBirth = new java.util.Date(o_result.getDate("studentDateOfBirth").getTime());
            int i_resultAdmissionYear = o_result.getInt("studentAdmissionYear");
            double d_resultAdmissionGrade = o_result.getDouble("studentAdmissionGrade");
            String str_resultTrainingSite = o_result.getString("trainingSite");
            String str_resultDepartmentID = o_result.getString("departmentID");
            String str_departmentName = o_result.getString("departmentName");
            if (str_resultTrainingSite == null)
                return new RegularStudent(i_resultStudentID, str_resultStudentName, o_resultDateOfBirth, i_resultAdmissionYear, d_resultAdmissionGrade);
            else
                return new ServiceStudent(i_resultStudentID, str_resultStudentName, o_resultDateOfBirth, i_resultAdmissionYear, d_resultAdmissionGrade, str_resultTrainingSite);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private Student toRegularStudent(ResultSet o_result){
        try {
            int i_resultStudentID = o_result.getInt("studentID");
            String str_resultStudentName = o_result.getString("studentName");
            java.util.Date o_resultDateOfBirth = new java.util.Date(o_result.getDate("studentDateOfBirth").getTime());
            int i_resultAdmissionYear = o_result.getInt("studentAdmissionYear");
            double d_resultAdmissionGrade = o_result.getDouble("studentAdmissionGrade");
            String str_resultDepartmentID = o_result.getString("departmentID");
            String str_departmentName = o_result.getString("departmentName");
            return new RegularStudent(i_resultStudentID, str_resultStudentName, o_resultDateOfBirth, i_resultAdmissionYear, d_resultAdmissionGrade);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private Student toServiceStudent(ResultSet o_result){
        try {
            int i_resultStudentID = o_result.getInt("studentID");
            String str_resultStudentName = o_result.getString("studentName");
            java.util.Date o_resultDateOfBirth = new java.util.Date(o_result.getDate("studentDateOfBirth").getTime());
            int i_resultAdmissionYear = o_result.getInt("studentAdmissionYear");
            double d_resultAdmissionGrade = o_result.getDouble("studentAdmissionGrade");
            String str_resultTrainingSite = o_result.getString("trainingSite");
            String str_resultDepartmentID = o_result.getString("departmentID");
            String str_departmentName = o_result.getString("departmentName");
            return new ServiceStudent(i_resultStudentID, str_resultStudentName, o_resultDateOfBirth, i_resultAdmissionYear, d_resultAdmissionGrade, str_resultTrainingSite);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
