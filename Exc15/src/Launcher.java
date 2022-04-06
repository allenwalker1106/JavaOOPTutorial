import Entity.Student;
import Model.StudentDAO;

import java.sql.*;
import java.util.HashMap;

public class Launcher {
    public static void main(String args[]){
        StudentDAO o_studentDAO = new StudentDAO();
        HashMap<Integer,Student> c_students = o_studentDAO.getServiceStudent();
        c_students.forEach((k,v)->System.out.println(v));
    }
}
