package Model;

import Entity.RegularStudent;
import Entity.Result;
import Entity.ServiceStudent;
import Entity.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentDAO {

    public StudentDAO(){

    }

    public boolean insertStudent(Student o_student){
        boolean b_success = false;
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            if (o_student instanceof RegularStudent) {
                String str_query = "{CALL InsertRegularStudent(?,?,?,?,?,?)}";
                CallableStatement o_statement = o_connection.prepareCall(str_query);
                o_statement.setInt("studentID",o_student.getStudentID());
                o_statement.setString("studentName",o_student.getName());
                o_statement.setDate("studentDateOfBirth",new java.sql.Date(o_student.getBirthDate().getTime()));
                o_statement.setInt("studentAdmissionYear",o_student.getAdmissionYear());
                o_statement.setDouble("studentAdmissionGrade",o_student.getAdmissionGrade());
                o_statement.setString("departmentID", o_student.getDepartmentID());
                o_statement.execute();
            } else if (o_student instanceof ServiceStudent) {
                String str_query = "{CALL InsertInServiceStudent(?,?,?,?,?,?,?)}";
                CallableStatement o_statement = o_connection.prepareCall(str_query);
                o_statement.setInt("studentID",o_student.getStudentID());
                o_statement.setString("studentName",o_student.getName());
                o_statement.setDate("studentDateOfBirth",new java.sql.Date(o_student.getBirthDate().getTime()));
                o_statement.setInt("studentAdmissionYear",o_student.getAdmissionYear());
                o_statement.setDouble("studentAdmissionGrade",o_student.getAdmissionGrade());
                o_statement.setString("departmentID",o_student.getDepartmentID());
                o_statement.setString("trainingSite",((ServiceStudent) o_student).getTrainingSite());
                o_statement.execute();
            }
            b_success=true;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return b_success;
    }

    public boolean deleterStudent(int i_studentID){
        boolean b_success = false;
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try{
            String str_query = "{CALL DeleteRegularStudent(?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setInt("studentID",i_studentID);
            o_statement.execute();
            str_query = "{CALL DeleteInServiceStudent(?)}";
            o_statement = o_connection.prepareCall(str_query);
            o_statement.setInt("studentID",i_studentID);
            o_statement.execute();
            b_success=true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return b_success;
    }

    public Student getStudent(int i_studentID){
        Student o_student = null;
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            String str_query="{CALL GetStudentByID(?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setInt("studentID",i_studentID);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                o_student= toStudent(o_result);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return o_student;
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
                Student o_student= toStudent(o_result);
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

    public HashMap<Integer,Student> getServiceStudents(){
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

    public HashMap<Integer,Student> getServiceStudentByTrainingSite(String str_trainingSite){
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        HashMap<Integer,Student> c_students = new HashMap<>();
        try{
            String str_query="{CALL GetServiceStudentByTrainingSite(?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setString("trainingSite",str_trainingSite);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                Student o_student= toStudent(o_result);
                c_students.put(o_student.getStudentID(),o_student);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return c_students;
    }

    HashMap<String,Integer> GetDepartmentStudentNumber(){
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        HashMap<String,Integer> c_count = new HashMap<>();
        try{
            String str_query="{CALL GetDepartmentStudentNumber()}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                String str_departmentID=o_result.getString("departmentID");
                int i_studentNumber = o_result.getInt(2);
                c_count.put(str_departmentID,i_studentNumber);

            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return c_count;
    }

    public HashMap<String,Integer> getDepartmentRegularStudentCount(){
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        HashMap<String,Integer> c_results= new HashMap<>();
        try{
            String str_query = "{CALL GetDepartmentRegularStudentNumber()}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.execute();
            ResultSet o_resultSet = o_statement.getResultSet();
            while(o_resultSet.next()){
                String str_departmentID = o_resultSet.getString("departmentID");
                int i_studentCount = o_resultSet.getInt(2);
                c_results.put(str_departmentID,i_studentCount);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return c_results;
    }

    public HashMap<String,Integer> getDepartmentTopStudent(){
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        HashMap<String,Integer> c_results= new HashMap<>();
        try{
            String str_query = "{CALL GetDepartmentMaxAdmissionGrade()}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.execute();
            ResultSet o_resultSet = o_statement.getResultSet();
            while(o_resultSet.next()){
                String str_departmentID = o_resultSet.getString("departmentID");
                int i_studentCount = o_resultSet.getInt(2);
                c_results.put(str_departmentID,i_studentCount);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return c_results;
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
                return new RegularStudent(i_resultStudentID, str_resultStudentName, o_resultDateOfBirth, i_resultAdmissionYear, d_resultAdmissionGrade,str_resultDepartmentID);
            else
                return new ServiceStudent(i_resultStudentID, str_resultStudentName, o_resultDateOfBirth, i_resultAdmissionYear, d_resultAdmissionGrade,str_resultDepartmentID, str_resultTrainingSite);
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
            return new RegularStudent(i_resultStudentID, str_resultStudentName, o_resultDateOfBirth, i_resultAdmissionYear, d_resultAdmissionGrade,str_resultDepartmentID);
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
            return new ServiceStudent(i_resultStudentID, str_resultStudentName, o_resultDateOfBirth, i_resultAdmissionYear, d_resultAdmissionGrade,str_resultDepartmentID, str_resultTrainingSite);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
