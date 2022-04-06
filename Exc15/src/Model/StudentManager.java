package Model;

import Entity.RegularStudent;
import Entity.Result;
import Entity.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class StudentManager {
    private  static volatile StudentManager o_instance;
    private StudentDAO o_studentDAO;
    private ResultDAO o_resultDAO;

    private StudentManager(){
        o_studentDAO= new StudentDAO();
        o_resultDAO = new ResultDAO();
    }

    public static synchronized  StudentManager getInstance(){
        if(o_instance==null){
            o_instance = new StudentManager();
        }
        return o_instance;
    }

    public boolean insertStudent(Student o_student){
        boolean b_success = false;
        b_success=this.o_studentDAO.insertStudent(o_student);
        ArrayList<Result> c_results = o_student.getResults();
        for(Result result: c_results){
            b_success=this.o_resultDAO.insertResult(result);
        }
        return b_success;
    }

    public boolean deleteStudent(int i_studentID){
        boolean b_success = false;
        b_success = this.o_studentDAO.deleterStudent(i_studentID);
        return b_success;
    }

    public boolean insertResult(int i_studentID,double d_averageGrade,int i_semesterID){
        boolean b_success =false;
        b_success= this.o_resultDAO.insertResult(i_studentID,d_averageGrade,i_semesterID);
        return b_success;
    }

    public boolean insertResult(Result o_result){
        boolean b_success = false;
        b_success= this.o_resultDAO.insertResult(o_result);
        return b_success;
    }

    public Student getStudent(int i_studentID){
        Student o_student =  this.o_studentDAO.getStudent(i_studentID);
        o_student.setResults(this.o_resultDAO.getResultByStudentId(o_student.getStudentID()));
        return o_student;
    }

    public HashMap<Integer,Student> getStudents(){
        HashMap<Integer,Student>  c_students =  this.o_studentDAO.getStudents();

        for(Integer studentID : c_students.keySet()){
            c_students.get(studentID).setResults(this.o_resultDAO.getResultByStudentId(studentID));
        }
        return c_students;
    }

    public boolean isRegularStudent(Student o_student){
        return o_student instanceof RegularStudent;
    }

    public boolean isRegularStudent(int i_studentID){
        return this.getStudent(i_studentID) instanceof RegularStudent;
    }

    public Result getStudentResultBySemester(int i_studentID,int i_semesterID){
        return this.o_resultDAO.getResultByStudentSemester(i_studentID,i_semesterID);
    }

    public HashMap<String,Integer> getDepartmentRegularStudentCount(){
        return this.o_studentDAO.getDepartmentRegularStudentCount();
    }


    public HashMap<String,Integer> getDepartmentTopStudent(){
        return this.o_studentDAO.getDepartmentTopStudent();
    }

    public HashMap<String,HashMap<Integer,Student>> getServiceStudentByTrainingSite(String str_trainingSite){
        HashMap<Integer,Student> c_students =  this.o_studentDAO.getServiceStudentByTrainingSite(str_trainingSite);
        for(Integer studentID : c_students.keySet()){
            c_students.get(studentID).setResults(this.o_resultDAO.getResultByStudentId(studentID));
        }
        HashMap<String,HashMap<Integer,Student>> c_result =new HashMap<>();
        for(Integer studentID : c_students.keySet()){
            String departmentID=c_students.get(studentID).getDepartmentID();
            if(c_result.containsKey(departmentID)){
                c_result.get(departmentID).put(studentID,c_students.get(studentID));
            }else{
                c_result.put(departmentID,new HashMap<>());
                c_result.get(departmentID).put(studentID,c_students.get(studentID));
            }
        }
        return c_result;
    }

    public HashMap<String,HashMap<Integer,Student>>  getDepartmentStudentByGrade(double d_averageGrade){
        HashMap<Integer,Student> c_students =  this.getStudents();
        HashMap<String,HashMap<Integer,Student>> c_department =new HashMap<>();
        for(Integer studentID : c_students.keySet()){
            ArrayList<Result> c_results = this.o_resultDAO.getResultByStudentId(studentID);
            c_students.get(studentID).setResults(c_results);
            if(c_results.get(0).getAverageGrade()>d_averageGrade);
            String departmentID=c_students.get(studentID).getDepartmentID();
            if(c_department.containsKey(departmentID)){
                c_department.get(departmentID).put(studentID,c_students.get(studentID));
            }else{
                c_department.put(departmentID,new HashMap<>());
                c_department.get(departmentID).put(studentID,c_students.get(studentID));
            }
        }
        return c_department;
    }

    public ArrayList<Student> getSortedStudent(Comparator<Student> fnc_comp){
        HashMap<Integer,Student> c_students =  this.getStudents();
        ArrayList<Student> c_sortedStudents = new ArrayList<>();
        for(Integer studentID : c_students.keySet()){
            c_sortedStudents.add(c_students.get(studentID));
        }
        c_sortedStudents.sort(fnc_comp);
        return c_sortedStudents;
    }

    public HashMap<String,Integer> GetDepartmentStudentNumber(){
        return this.o_studentDAO.GetDepartmentStudentNumber();
    }

}
