package DAO;

import Entity.RegularStudent;
import Entity.ServiceStudent;
import Entity.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentManagerDao {

    public StudentManagerDao(){

    }

    private Student toStudent(ResultSet o_result) throws SQLException{
        int i_resultStudentID = o_result.getInt("studentID");
        String str_resultStudentName = o_result.getString("studentName");
        java.util.Date o_resultDateOfBirth = new java.util.Date(o_result.getDate("studentDateOfBirth").getTime());
        int i_resultAdmissionYear = o_result.getInt("studentAdmissionYear");
        double d_resultAdmissionGrade = o_result.getDouble("studentAdmissionGrade");
        String str_resultDepartmentID = o_result.getString("departmentID");
        String str_departmentName = o_result.getString("departmentName");
        String str_resultTrainingSite;
        try{
            str_resultTrainingSite= o_result.getString("trainingSite");
        }catch (SQLException e){
//            e.printStackTrace();
            str_resultTrainingSite =null;
        }

        if (str_resultTrainingSite == null)
            return new RegularStudent(i_resultStudentID, str_resultStudentName, o_resultDateOfBirth, i_resultAdmissionYear, d_resultAdmissionGrade,str_resultDepartmentID);
        else
            return new ServiceStudent(i_resultStudentID, str_resultStudentName, o_resultDateOfBirth, i_resultAdmissionYear, d_resultAdmissionGrade,str_resultDepartmentID, str_resultTrainingSite);
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
        }finally {
            try{
                o_connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return b_success;
    }

    public boolean deleteStudent(int i_studentID){
        boolean b_success = false;
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try{
            String str_query = "{CALL DeleteStudent(?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setInt("studentID",i_studentID);
            o_statement.execute();
            b_success=true;
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                o_connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return b_success;
    }

    public boolean updateStudent(Student o_student){
        boolean b_success = false;
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            if (o_student instanceof RegularStudent) {
                String str_query = "{CALL UpdateRegularStudent(?,?,?,?,?,?)}";
                CallableStatement o_statement = o_connection.prepareCall(str_query);
                o_statement.setInt("studentID",o_student.getStudentID());
                o_statement.setString("studentName",o_student.getName());
                o_statement.setDate("studentDateOfBirth",new java.sql.Date(o_student.getBirthDate().getTime()));
                o_statement.setInt("studentAdmissionYear",o_student.getAdmissionYear());
                o_statement.setDouble("studentAdmissionGrade",o_student.getAdmissionGrade());
                o_statement.setString("departmentID", o_student.getDepartmentID());
                o_statement.execute();
            } else if (o_student instanceof ServiceStudent) {
                String str_query = "{CALL UpdateInServiceStudent(?,?,?,?,?,?,?)}";
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
        }finally {
            try{
                o_connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return b_success;
    }

    public Student getStudentById(int i_studentID){
        Student o_student =null;
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
        }
        catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                o_connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return o_student;
    }

    public String toDepartmentID(ResultSet o_result) throws SQLException{
        return o_result.getString("departmentID");
    }

    public int toAdmissionYear(ResultSet o_result) throws SQLException{
        return o_result.getInt("studentAdmissionYear");
    }

    public int toCounter(ResultSet o_result) throws SQLException{
        return o_result.getInt("counter");
    }

    public double toDoubleCounter(ResultSet o_result) throws SQLException{
        return o_result.getDouble("counter");
    }

    public ArrayList<Student> getAllStudent(){
        ArrayList<Student> c_students = new ArrayList<Student>();
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            String str_query="{CALL GetAllStudent()}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                c_students.add(toStudent(o_result));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                o_connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return c_students;
    }

    public ArrayList<Student> getRegularStudent(){
        ArrayList<Student> c_students = new ArrayList<Student>();
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            String str_query="{CALL GetRegularStudent()}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                c_students.add(toStudent(o_result));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                o_connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return c_students;
    }

    public ArrayList<Student> getServiceStudent(){
        ArrayList<Student> c_students = new ArrayList<Student>();
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            String str_query="{CALL GetServiceStudent()}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                c_students.add(toStudent(o_result));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                o_connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return c_students;
    }

    public ArrayList<Student> getStudentByDepartment(String str_departmentID){
        ArrayList<Student> c_students = new ArrayList<Student>();
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            String str_query="{CALL GetStudentByDepartment(?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setString("departmentID",str_departmentID);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                c_students.add(toStudent(o_result));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                o_connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return c_students;
    }

    public ArrayList<Student> getStudentByTrainingSite(String str_trainingSite){
        ArrayList<Student> c_students = new ArrayList<Student>();
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            String str_query="{CALL GetStudentByTrainingSite(?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setString("trainingSite",str_trainingSite);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                c_students.add(toStudent(o_result));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                o_connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return c_students;
    }

    public HashMap<String,Integer> getDepartmentRegularCount(){
        HashMap<String,Integer> c_department = new HashMap<String,Integer>();
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            String str_query="{CALL GetDepartmentRegularCount()}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                String str_departmentID =toDepartmentID(o_result);
                int i_counter = toCounter(o_result);
                c_department.put(str_departmentID,i_counter);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                o_connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return c_department;
    }

    public HashMap<String,Double> getDepartmentTopAdmissionGrade(){
        HashMap<String,Double> c_department = new HashMap<String,Double>();
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            String str_query="{CALL GetDepartmentTopAdmissionGrade()}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                String str_departmentID =toDepartmentID(o_result);
                double d_counter = toDoubleCounter(o_result);
                c_department.put(str_departmentID,d_counter);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                o_connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return c_department;
    }

    public ArrayList<Student> getStudentByDepartmentAdmissionGrade(String str_departmentID,double d_admissionGrade){
        ArrayList<Student> c_students = new ArrayList<Student>();
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            String str_query="{CALL GetStudentByDepartmentAdmissionGrade(?,?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setString("departmentID",str_departmentID);
            o_statement.setDouble("studentAdmissionGrade",d_admissionGrade);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                c_students.add(toStudent(o_result));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                o_connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return c_students;
    }

    public HashMap<String,ArrayList<Student>> getDepartmentTopAdmissionStudent(){
        HashMap<String,ArrayList<Student>> c_department = new HashMap<>();
        HashMap<String,Double> c_departmentTopAdmissionGrade =this.getDepartmentTopAdmissionGrade();
        for(String str_departmentID:c_departmentTopAdmissionGrade.keySet()){
            double d_admissionGrade = c_departmentTopAdmissionGrade.get(str_departmentID);
            ArrayList<Student> c_students=this.getStudentByDepartmentAdmissionGrade(str_departmentID,d_admissionGrade);
            c_department.put(str_departmentID,c_students);
        }
        return c_department;
    }

    public HashMap<String,HashMap<Integer,Integer>> getDepartmentStatisticByYear(){
        HashMap<String,HashMap<Integer,Integer>> c_department = new HashMap<String,HashMap<Integer,Integer>>();
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            String str_query="{CALL GetDepartmentStatisticByYear()}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                String str_departmentID =toDepartmentID(o_result);
                int i_toAdmissionYear =toAdmissionYear(o_result);
                int i_counter = toCounter(o_result);
                if(c_department.containsKey(str_departmentID)){
                    c_department.get(str_departmentID).put(i_toAdmissionYear,i_counter);
                }else{
                    c_department.put(str_departmentID,new HashMap<Integer,Integer>());
                    c_department.get(str_departmentID).put(i_toAdmissionYear,i_counter);
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }finally {
            try{
                o_connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return c_department;
    }

}
