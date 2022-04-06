package Model;

import Entity.RegularStudent;
import Entity.Result;
import Entity.ServiceStudent;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ResultDAO {
    public ArrayList<Result> getResultByStudentId(int i_studentID){
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        ArrayList<Result> c_results = new ArrayList<>();
        try {
            String str_query="{CALL GetResultByStudentID(?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setInt(1,i_studentID);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                c_results.add(toResult(o_result));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return c_results;
    }

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
        }
        return b_success;
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
        }
        return b_success;
    }

    public boolean deleteResult(int i_resultID){
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        boolean b_success = false;
        try{
            String str_query = "{CALL deleteResult(?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setInt("resultID",i_resultID);
            o_statement.execute();
            b_success=true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return b_success;
    }



    public ArrayList<Result> getResultBySemester(int i_semesterID){
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        ArrayList<Result> c_results = new ArrayList<>();
        try {
            String str_query="{CALL GetResultBySemesterID(?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setInt(1,i_semesterID);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                c_results.add(toResult(o_result));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return c_results;
    }

    public Result getResultByStudentSemester(int i_studentID,int i_semesterID){
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        Result o_result =null;
        ArrayList<Result> c_results = new ArrayList<>();
        try {
            String str_query="{CALL GetResultByStudentIDSemesterID(?,?)}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.setInt(1,i_studentID);
            o_statement.setInt(2,i_semesterID);
            o_statement.execute();
            ResultSet o_resultSet = o_statement.getResultSet();
            while(o_resultSet.next()){
                o_result=toResult(o_resultSet);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return o_result;
    }

    public ArrayList<Result> getResults(){
        Connection o_connection = SchoolDatabaseConnection.getInstance().getConnection();
        ArrayList<Result> c_results = new ArrayList<>();
        try {
            String str_query="{CALL GetResults()}";
            CallableStatement o_statement = o_connection.prepareCall(str_query);
            o_statement.execute();
            ResultSet o_result = o_statement.getResultSet();
            while(o_result.next()){
                c_results.add(toResult(o_result));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return c_results;
    }



    private Result toResult(ResultSet o_result){
        try {
            int i_resultStudentID = o_result.getInt("studentID");
            int i_resultResultID =  o_result.getInt("resultID");
            double d_resultAverageGrade = o_result.getDouble("averageGrade");
            int i_resultSemesterID =  o_result.getInt("semesterID");
            String str_resultSemesterName = o_result.getString("semesterName");
            java.util.Date o_resultSemesterDate = new java.util.Date(o_result.getDate("semesterDate").getTime());
            return new Result(i_resultStudentID,i_resultResultID,d_resultAverageGrade,i_resultSemesterID,str_resultSemesterName,o_resultSemesterDate);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }


}
