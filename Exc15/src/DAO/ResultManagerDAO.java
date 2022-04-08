package DAO;

import Entity.RegularStudent;
import Entity.Result;
import Entity.ServiceStudent;
import Entity.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultManagerDAO {
    public boolean insertResult(int i_studentID,double d_averageGrade,int i_semesterID){
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        boolean b_success = false;
        try{
            String str_query = "{CALL InsertResult(?,?,?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setInt("studentID",i_studentID);
            o_statement.setDouble("averageGrade",d_averageGrade);
            o_statement.setInt("semesterID",i_semesterID);
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

    private Result toResult(ResultSet o_result) throws SQLException{
        int i_resultStudentID = o_result.getInt("studentID");
        int i_resultResultID =  o_result.getInt("resultID");
        double d_resultAverageGrade = o_result.getDouble("averageGrade");
        int i_resultSemesterID =  o_result.getInt("semesterID");
        String str_resultSemesterName = o_result.getString("semesterName");
        java.util.Date o_resultSemesterDate = new java.util.Date(o_result.getDate("semesterDate").getTime());
        return new Result(i_resultStudentID,i_resultResultID,d_resultAverageGrade,i_resultSemesterID,str_resultSemesterName,o_resultSemesterDate);
    }

    public boolean insertResult(Result o_result){
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        boolean b_success = false;
        try{
            String str_query = "{CALL InsertResult(?,?,?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setInt("studentID",o_result.getStudentID());
            o_statement.setDouble("averageGrade",o_result.getAverageGrade());
            o_statement.setInt("semesterID",o_result.getSemesterID());
            o_statement.execute();
            b_success=true;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                o_connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return b_success;
    }

    public boolean insertResults(ArrayList<Result> c_results){
        boolean b_success =false;
        for(int i=0;i<c_results.size();i++)
            b_success =insertResult(c_results.get(i));
        return b_success;
    }

    public boolean deleteResult(int i_studentID,int i_semesterID){
        boolean b_success = false;
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try{
            String str_query = "{CALL DeleteResult(?,?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setInt("studentID",i_studentID);
            o_statement.setInt("semesterID",i_semesterID);
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

    public boolean updateResult(Result o_result ){

        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        boolean b_success = false;
        try{
            String str_query = "{CALL UpdateResult(?,?,?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setInt("studentID",o_result.getStudentID());
            o_statement.setDouble("averageGrade",o_result.getAverageGrade());
            o_statement.setInt("semesterID",o_result.getSemesterID());
            o_statement.execute();
            b_success=true;
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                o_connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return b_success;
    }

    public boolean updateResults(ArrayList<Result> c_results){
        boolean b_success =false;
        for(int i=0;i<c_results.size();i++)
            b_success =updateResult(c_results.get(i));
        return b_success;

    }

    public ArrayList<Result> getResultsByStudentID(int i_studentID) {
        ArrayList<Result> c_result = new ArrayList<Result>();
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            String str_query="{CALL GetResultByStudentID(?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setInt("studentID",i_studentID);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                c_result.add(toResult(o_result));
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
        return c_result;
    }

    public ArrayList<Result> getAllResult(){
        ArrayList<Result> c_result = new ArrayList<Result>();
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            String str_query="{CALL GetAllResult()}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                c_result.add(toResult(o_result));
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
        return c_result;
    }

    public ArrayList<Result> getResultBySemester(int i_semesterID){ArrayList<Result> c_result = new ArrayList<Result>();
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            String str_query="{CALL GetResultBySemester(?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setInt("semesterID",i_semesterID);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                c_result.add(toResult(o_result));
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
        return c_result;
    }

    public ArrayList<Result> getRegularStudentResult(){
        ArrayList<Result> c_result = new ArrayList<Result>();
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            String str_query="{CALL GetRegularStudentResult()}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                c_result.add(toResult(o_result));
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
        return c_result;
    }

    public ArrayList<Result> getServiceStudentResult(){
        ArrayList<Result> c_result = new ArrayList<Result>();
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            String str_query="{CALL GetServiceStudentResult()}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                c_result.add(toResult(o_result));
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
        return c_result;
    }

    public ArrayList<Result> getResultByDepartment(String str_departmentID){ArrayList<Result> c_result = new ArrayList<Result>();
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            String str_query="{CALL GetResultByDepartment(?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setString("departmentID",str_departmentID);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                c_result.add(toResult(o_result));
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
        return c_result;
    }

    public ArrayList<Result>  getStudentResultBySemester(int i_studentID, int i_semseterID){
        ArrayList<Result> c_result = new ArrayList<Result>();
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        try {
            String str_query="{CALL GetStudentResultBySemester(?,?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setInt("studentID",i_studentID);
            o_statement.setInt("semesterID",i_semseterID);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                c_result.add(toResult(o_result));
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
        return c_result;
    }

}
