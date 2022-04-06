import Entity.RegularStudent;
import Entity.Result;
import Entity.ServiceStudent;
import Entity.Student;
import Model.StudentManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

public class Launcher {
    public static void main(String args[]){
        StudentManager o_studentManager = StudentManager.getInstance();
        // test Add regular Student
//        Student o_student = new RegularStudent(18021048, "Bùi Vĩ Quân", new Date(), 2022, 25, "DTVT");
//        o_studentManager.insertStudent(o_student);


////        //test add inservice student
//        Student o_student = new ServiceStudent(18021049, "Bùi Vĩ Quân", new Date(), 2022, 25, "DTVT","HN");
//        o_studentManager.insertStudent(o_student);

//        test delete student


//        //test get Student ByID
//        Student o_student = o_studentManager.getStudent(17020154);
//        System.out.println(o_student.toString());

//        //test get All student
//        HashMap<Integer,Student> c_students = o_studentManager.getStudents();
//        System.out.println(c_students);


//        //test check RegularStudent
//        boolean isRegularStudent = o_studentManager.isRegularStudent(17020154);
//        System.out.println(isRegularStudent);
//        isRegularStudent = o_studentManager.isRegularStudent(17020012);
//        System.out.println(isRegularStudent);


//        //test get student result by semester id
//        Result o_result = o_studentManager.getStudentResultBySemester(17020933,20211);
//        System.out.println(o_result);


//        //test getDepartment regular student COunt
//        HashMap<String,Integer> c_sudentCount = o_studentManager.getDepartmentRegularStudentCount();
//        c_sudentCount.forEach((k,v)->System.out.println(k+" :"+v));

//        // test get department top admission Grade
//        HashMap<String,Integer> c_grade =  o_studentManager.getDepartmentTopStudent();
//        c_grade.forEach((k,v)->System.out.println(k+" :"+v));


//        //test get service student by training site
//        HashMap<String,HashMap<Integer,Student>> c_department = o_studentManager.getServiceStudentByTrainingSite("HN");
//        c_department.forEach((departmentID,c_student)->{
//            System.out.println(departmentID+":\n");
//            c_student.forEach((studentID,student)->System.out.print(studentID+" , "));
//            System.out.print("\n");
//        });


//        // test get student by grade threshold
//        HashMap<String,HashMap<Integer,Student>> c_department = o_studentManager.getDepartmentStudentByGrade(8.0);
//        c_department.forEach((departmentID,c_student)->{
//            System.out.println(departmentID+":\n");
//            c_student.forEach((studentID,student)->System.out.print(studentID+" , "));
//            System.out.print("\n");
//        });

//        //test get sorted student
//        ArrayList<Student> c_student = o_studentManager.getSortedStudent((s1,s2)->{
//            if(s1 instanceof RegularStudent && s2 instanceof ServiceStudent)
//                return 1;
//            else if (s1 instanceof ServiceStudent && s2 instanceof RegularStudent)
//                return -1;
//            else{
//                return s2.getAdmissionYear()-s1.getAdmissionYear();
//            }
//        });
//
//        c_student.forEach((student)-> {
//            System.out.print(student.getStudentID()+" ");
//            if (student instanceof RegularStudent)
//                System.out.print("Regular :");
//            else
//                System.out.print("Service :");
//            System.out.println(student.getAdmissionYear());
//        });


//        //test get department statistic
//        HashMap<String,Integer> c_departments = o_studentManager.GetDepartmentStudentNumber();
//        c_departments.forEach((k,v)->System.out.println(k+" : "+v));
    }
}
