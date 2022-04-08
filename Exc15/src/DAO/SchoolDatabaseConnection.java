package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class SchoolDatabaseConnection {
    private static volatile SchoolDatabaseConnection o_instance;
    private static volatile ArrayList<Connection> c_connectionPool;
    private static String str_connectHost;
    private static String str_databaseName;
    private static String str_userName;
    private static String str_password;
    private static String str_connectURl;
    private static final int i_poolSize =10;
    private SchoolDatabaseConnection(){
        str_connectHost="jdbc:mysql:// localhost:3306/";
        str_databaseName="schooldb";
        str_userName="schoolad";
        str_password="%^H*(n^&1@3";
        c_connectionPool = new ArrayList<>(i_poolSize);
        str_connectURl = str_connectHost+str_databaseName;
        for(int i=0;i<i_poolSize;i++){
            try {
                c_connectionPool.add(DriverManager.getConnection(str_connectURl, str_userName, str_password));
                c_connectionPool.get(i).close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized  SchoolDatabaseConnection getInstance(){
        if(o_instance==null){
            o_instance = new SchoolDatabaseConnection();
        }
        return o_instance;
    }

    public static synchronized Connection getConnection(){
        while(true){
            for(Connection c : c_connectionPool){
                try{
                    if(c.isClosed()){
                        c=DriverManager.getConnection(str_connectURl, str_userName, str_password);
                        return c;
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

}
