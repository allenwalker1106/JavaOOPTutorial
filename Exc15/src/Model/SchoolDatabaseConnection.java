package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SchoolDatabaseConnection {
    private  static volatile SchoolDatabaseConnection o_instance;
    private  Connection o_connection;
    private SchoolDatabaseConnection(){
        String str_connectHost="jdbc:mysql:// localhost:3306/";
        String str_databaseName="schooldb";
        String str_userName="schoolad";
        String str_password="%^H*(n^&1@3";
        try{
//            Class.forName("com.mysql.jdbc.Driver");
            String str_connectURl = str_connectHost+str_databaseName;
            o_connection = DriverManager.getConnection(str_connectURl,str_userName,str_password);
        }catch( SQLException e){
            e.printStackTrace();
        }
    }

    public static synchronized  SchoolDatabaseConnection getInstance(){
        if(o_instance==null){
            o_instance = new SchoolDatabaseConnection();
        }
        return o_instance;
    }

    public Connection getConnection(){
        return o_connection;
    }
}
