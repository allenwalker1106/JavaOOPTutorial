package Server;

import DAO.ResultManagerDAO;
import DAO.StudentManagerDao;
import Entity.RegularStudent;
import Entity.Result;
import Entity.ServiceStudent;
import Entity.Student;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class RequestHandle {
    private StudentManagerDao o_studentDAO;
    private ResultManagerDAO o_resultDAO;

    public RequestHandle(){
        this.o_studentDAO=new StudentManagerDao();
        this.o_resultDAO = new ResultManagerDAO();
    }

    private double getHighestResutlt(ArrayList<Result> c_results){
        double d_grade =0;
        for(Result o_result : c_results){
            if(o_result.getAverageGrade()>d_grade)
                d_grade = o_result.getAverageGrade();
        }
        return d_grade;
    }

    private  Student JSONToStudent(JSONObject js_data) throws JSONException{
        String str_type = js_data.getString("type");
        int i_studentID = js_data.getInt("studentID");
        String str_studentName = js_data.getString("studentName");
        Date o_studentDateOfBirth = new Date();
        try{
            o_studentDateOfBirth = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(js_data.getString("studentDateOfBirth"));
        }catch(ParseException e){
            e.printStackTrace();
        }
        int i_studentAdmissionYear = js_data.getInt("studentAdmissionYear");
        double d_studentAdmissionGrade = js_data.getDouble("studentAdmissionGrade");
        String str_departmentID = js_data.getString("departmentID");
        if(str_type.trim().equalsIgnoreCase("service")){
            String str_trainingSite = js_data.getString("trainingSite");
            return new ServiceStudent(i_studentID,str_studentName,o_studentDateOfBirth,i_studentAdmissionYear,d_studentAdmissionGrade,str_departmentID,str_trainingSite);
        }else
            return new RegularStudent(i_studentID,str_studentName,o_studentDateOfBirth,i_studentAdmissionYear,d_studentAdmissionGrade,str_departmentID);
    }

    private JSONObject studentToJSON(Student o_student) throws JSONException{
        JSONObject js_student = new JSONObject();
        if(o_student!=null){
            js_student.put("studentID",o_student.getStudentID());
            js_student.put("studentName",o_student.getName());
            js_student.put("studentDateOfBirth",o_student.getBirthDate().toString());
            js_student.put("studentAdmissionYear",o_student.getAdmissionYear());
            js_student.put("studentAdmissionGrade",o_student.getAdmissionGrade());
            js_student.put("departmentID",o_student.getDepartmentID());
            try{
                js_student.put("trainingSite",((ServiceStudent) o_student).getTrainingSite());
            }catch(Exception e){

            }
            js_student.put("results",resultsToJSON(o_student.getResults()));
        }else
            return null;
        return js_student;
    }

    private  JSONArray studentsToJSON(ArrayList<Student> c_students) throws JSONException{
        JSONArray js_students = new JSONArray();
        for(Student c_student : c_students){
            js_students.put(studentToJSON(c_student));
        }
        return js_students;
    }

    private JSONObject resultToJSON(Result o_result) throws JSONException{
        JSONObject js_result = new JSONObject();
        if(o_result!=null){
            js_result.put("studentID",o_result.getStudentID());
            js_result.put("resultID",o_result.getResultID());
            js_result.put("averageGrade",o_result.getAverageGrade());
            js_result.put("semesterID",o_result.getSemesterID());
            js_result.put("semesterName",o_result.getSemesterName());
            js_result.put("semesterDate",o_result.getSemesterDate().toString());
        }else
            return null;
        return js_result;
    }

    private JSONArray resultsToJSON(ArrayList<Result> c_results) throws JSONException{
        JSONArray js_results = new JSONArray();
        for(Result c_result : c_results){
            js_results.put(resultToJSON(c_result));
        }
        return js_results;
    }

    public Result JSONToResult(JSONObject js_data, int i_studentID) throws JSONException{
        int i_semesterID = js_data.getInt("semesterID");
        double d_averageGrade = js_data.getDouble("averageGrade");
        return new Result(i_studentID,d_averageGrade,i_semesterID);
    }

    public ArrayList<Result> JSONToResults(JSONArray js_data,int i_studentID) throws JSONException{
        ArrayList<Result> c_results = new ArrayList<Result>();
        for(int i=0;i<js_data.length();i++)
            c_results.add(this.JSONToResult(js_data.getJSONObject(i),i_studentID));
        return c_results;
    }

    public JSONObject departmentToJSON(HashMap<String,Integer> c_departments) throws JSONException{
        JSONObject js_departments = new JSONObject();
        for(String str_departmentID:c_departments.keySet()){
            if(js_departments.has(str_departmentID))
                js_departments.getJSONArray(str_departmentID).put(c_departments.get(str_departmentID));
            else {
                js_departments.put(str_departmentID, new JSONArray());
                js_departments.getJSONArray(str_departmentID).put(c_departments.get(str_departmentID));
            }
        }
        return js_departments;
    }

    public JSONObject departmentStatisticToJSON(HashMap<String,HashMap<Integer,Integer>> c_departments) throws JSONException{
        JSONObject js_departments = new JSONObject();
        for(String str_departmentID:c_departments.keySet()){
            if(js_departments.has(str_departmentID))
                for(Integer year: c_departments.get(str_departmentID).keySet())
                    js_departments.getJSONObject(str_departmentID).put(year.toString(),c_departments.get(str_departmentID).get(year));
            else {
                js_departments.put(str_departmentID, new JSONObject());
                for(Integer year: c_departments.get(str_departmentID).keySet())
                    js_departments.getJSONObject(str_departmentID).put(year.toString(),c_departments.get(str_departmentID).get(year));
            }
        }
        return js_departments;
    }

    public JSONObject departmentStudentToJSON(HashMap<String,ArrayList<Student>> c_departments) throws JSONException{
        JSONObject js_departments = new JSONObject();
        for(String str_departmentID:c_departments.keySet()){
            js_departments.put(str_departmentID,studentsToJSON(c_departments.get(str_departmentID)));
        }
        return js_departments;
    }

    public JSONObject insertStudent(JSONObject js_data) throws JSONException {
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        Student o_student = JSONToStudent(js_data);
        ArrayList<Result> c_results = JSONToResults(js_data.getJSONArray("results"),o_student.getStudentID());
        b_success=o_studentDAO.insertStudent(o_student);
        if(b_success&&c_results.size()!=0){
            b_success = o_resultDAO.insertResults(c_results);
        }
        if(b_success){
            js_response.put("success",true);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject deleteStudent(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        int i_studentID = js_data.getInt("studentID");
        b_success=o_studentDAO.deleteStudent(i_studentID);
        if(b_success){
            js_response.put("success",true);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject updateStudent(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        Student o_student = JSONToStudent(js_data);
        b_success=o_studentDAO.updateStudent(o_student);
        if(b_success){
            js_response.put("success",true);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject insertResult(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        int i_studentID = js_data.getInt("studentID");
        ArrayList<Result> c_results = JSONToResults(js_data.getJSONArray("results"),i_studentID);
        b_success=b_success = o_resultDAO.insertResults(c_results);
        if(b_success&&c_results.size()!=0){
            js_response.put("success",true);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject deleteResult(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        int i_studentID = js_data.getInt("studentID");
        int i_semesterID = js_data.getInt("semesterID");
        b_success=b_success = o_resultDAO.deleteResult(i_studentID,i_semesterID);
        if(b_success){
            js_response.put("success",true);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject updateResult(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        int i_studentID = js_data.getInt("studentID");
        ArrayList<Result> c_results = JSONToResults(js_data.getJSONArray("results"),i_studentID);
        if(c_results.size()!=0)
            b_success=o_resultDAO.updateResults(c_results);
        if(b_success){
            js_response.put("success",true);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getStudentByID(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        int i_studentID = js_data.getInt("studentID");
        Student o_student=o_studentDAO.getStudentById(i_studentID);
        ArrayList<Result>o_results=o_resultDAO.getResultsByStudentID(i_studentID);
        o_student.setResults(o_results);
        JSONObject js_student = studentToJSON(o_student);
        if(js_student!=null){
            js_response.put("success",true);
            js_response.put("student",js_student);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getAllStudent(JSONObject js_data)throws JSONException{
        JSONObject js_response = new JSONObject();
        ArrayList<Student> c_students=o_studentDAO.getAllStudent();
        for(Student o_student: c_students){
            ArrayList<Result>o_results=o_resultDAO.getResultsByStudentID(o_student.getStudentID());
            o_student.setResults(o_results);
        }
        JSONArray js_students = studentsToJSON(c_students);
        if(js_students!=null){
            js_response.put("success",true);
            js_response.put("students",js_students);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getRegularStudent(JSONObject js_data)throws JSONException{
        JSONObject js_response = new JSONObject();
        ArrayList<Student> c_students=o_studentDAO.getRegularStudent();
        for(Student o_student: c_students){
            ArrayList<Result>o_results=o_resultDAO.getResultsByStudentID(o_student.getStudentID());
            o_student.setResults(o_results);
        }
        JSONArray js_students = studentsToJSON(c_students);
        if(js_students!=null){
            js_response.put("success",true);
            js_response.put("students",js_students);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getServiceStudent(JSONObject js_data)throws JSONException{
        JSONObject js_response = new JSONObject();
        ArrayList<Student> c_students=o_studentDAO.getServiceStudent();
        for(Student o_student: c_students){
            ArrayList<Result>o_results=o_resultDAO.getResultsByStudentID(o_student.getStudentID());
            o_student.setResults(o_results);
        }
        JSONArray js_students = studentsToJSON(c_students);
        if(js_students!=null){
            js_response.put("success",true);
            js_response.put("students",js_students);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getStudentByDepartment(JSONObject js_data)throws JSONException{
        JSONObject js_response = new JSONObject();
        String str_departmentID = js_data.getString("departmentID");
        ArrayList<Student> c_students=o_studentDAO.getStudentByDepartment(str_departmentID);
        for(Student o_student: c_students){
            ArrayList<Result>o_results=o_resultDAO.getResultsByStudentID(o_student.getStudentID());
            o_student.setResults(o_results);
        }
        JSONArray js_students = studentsToJSON(c_students);
        if(js_students!=null){
            js_response.put("success",true);
            js_response.put("students",js_students);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getStudentByTrainingSite(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        String str_trainingSite= js_data.getString("trainingSite");
        ArrayList<Student> c_students=o_studentDAO.getStudentByTrainingSite(str_trainingSite);
        for(Student o_student: c_students){
            ArrayList<Result>o_results=o_resultDAO.getResultsByStudentID(o_student.getStudentID());
            o_student.setResults(o_results);
        }
        JSONArray js_students = studentsToJSON(c_students);
        if(js_students!=null){
            js_response.put("success",true);
            js_response.put("students",js_students);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getResultByStudent(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        int i_studentID = js_data.getInt("studentID");
        ArrayList<Result>c_results=o_resultDAO.getResultsByStudentID(i_studentID);
        JSONArray js_results = resultsToJSON(c_results);
        b_success=true;
        if(b_success){
            js_response.put("success",true);
            js_response.put("results",js_results);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getAllResult(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        ArrayList<Result>c_results=o_resultDAO.getAllResult();
        JSONArray js_results = resultsToJSON(c_results);
        b_success=true;
        if(b_success){
            js_response.put("success",true);
            js_response.put("results",js_results);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getResultBySemester(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        int i_semesterID = js_data.getInt("semesterID");
        ArrayList<Result>c_results=o_resultDAO.getResultBySemester(i_semesterID);
        JSONArray js_results = resultsToJSON(c_results);
        b_success=true;
        if(b_success){
            js_response.put("success",true);
            js_response.put("results",js_results);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getRegularStudentResult(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        ArrayList<Result>c_results=o_resultDAO.getRegularStudentResult();
        JSONArray js_results = resultsToJSON(c_results);
        b_success=true;
        if(b_success){
            js_response.put("success",true);
            js_response.put("results",js_results);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getServiceStudentResult(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        ArrayList<Result>c_results=o_resultDAO.getServiceStudentResult();
        JSONArray js_results = resultsToJSON(c_results);
        b_success=true;
        if(b_success){
            js_response.put("success",true);
            js_response.put("results",js_results);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getResultByDepartment(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        String str_departmentID = js_data.getString("departmentID");
        ArrayList<Result>c_results=o_resultDAO.getResultByDepartment(str_departmentID);
        JSONArray js_results = resultsToJSON(c_results);
        b_success=true;
        if(b_success){
            js_response.put("success",true);
            js_response.put("results",js_results);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getStudentType(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        int i_studentID = js_data.getInt("studentID");
        Student o_student=o_studentDAO.getStudentById(i_studentID);
        String str_type="invalid";
        if(o_student instanceof RegularStudent)
            str_type = "regular";
        else
            str_type = "service";
        b_success=true;
        if(b_success){
            js_response.put("success",true);
            js_response.put("type",str_type);
        }else{
            js_response.put("success",false);
        }
        return js_response;

    }

    public JSONObject getStudentResultBySemester(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        int i_studentID =js_data.getInt("studentID");
        int i_semesterID= js_data.getInt("semesterID");
        ArrayList<Result>c_results=o_resultDAO.getStudentResultBySemester(i_studentID,i_semesterID);
        JSONArray js_results = resultsToJSON(c_results);
        b_success=true;
        if(b_success){
            js_response.put("success",true);
            js_response.put("results",js_results);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getDepartmentRegularCount(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        HashMap<String,Integer> c_results=o_studentDAO.getDepartmentRegularCount();
        JSONObject js_results = departmentToJSON(c_results);
        b_success=true;
        if(b_success){
            js_response.put("success",true);
            js_response.put("results",js_results);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getDepartmentTopAdmissionStudent(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        HashMap<String,ArrayList<Student>> c_results=o_studentDAO.getDepartmentTopAdmissionStudent();
        JSONObject js_results = departmentStudentToJSON(c_results);
        b_success=true;
        if(b_success){
            js_response.put("success",true);
            js_response.put("results",js_results);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getDepartmentStudentByGrade(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        double d_averageGrade = js_data.getDouble("averageGrade");
        HashMap<String,ArrayList<Student>> c_results = new HashMap<String,ArrayList<Student>>();
        ArrayList<Student> c_students=o_studentDAO.getAllStudent();
        for(Student o_student: c_students){
            ArrayList<Result>o_results=o_resultDAO.getResultsByStudentID(o_student.getStudentID());
            o_student.setResults(o_results);
            if(o_results.size()>0&&o_results.get(0).getAverageGrade()>d_averageGrade){
                String str_departmentID = o_student.getDepartmentID();
                if(c_results.containsKey(str_departmentID)){
                    c_results.get(str_departmentID).add(o_student);
                }else{
                    c_results.put(str_departmentID, new ArrayList<Student>());
                    c_results.get(str_departmentID).add(o_student);
                }
            }
        }

        JSONObject js_results = departmentStudentToJSON(c_results);
        b_success=true;
        if(b_success){
            js_response.put("success",true);
            js_response.put("results",js_results);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getDepartmentStudentByTrainingSite(JSONObject js_data) throws JSONException{
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        String str_trainingSite= js_data.getString("trainingSite");
        HashMap<String,ArrayList<Student>> c_results = new HashMap<String,ArrayList<Student>>();
        ArrayList<Student> c_students=o_studentDAO.getStudentByTrainingSite(str_trainingSite);
        for(Student o_student: c_students){
            ArrayList<Result>o_results=o_resultDAO.getResultsByStudentID(o_student.getStudentID());
            o_student.setResults(o_results);
            String str_departmentID = o_student.getDepartmentID();
            if(c_results.containsKey(str_departmentID)){
                c_results.get(str_departmentID).add(o_student);
            }else{
                c_results.put(str_departmentID, new ArrayList<Student>());
                c_results.get(str_departmentID).add(o_student);
            }
        }

        JSONObject js_results = departmentStudentToJSON(c_results);
        b_success=true;
        if(b_success){
            js_response.put("success",true);
            js_response.put("results",js_results);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getDepartmentTopSemesterStudent(JSONObject js_data) throws JSONException {
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        HashMap<String,ArrayList<Student>> c_results = new HashMap<String,ArrayList<Student>>();
        HashMap<String,Double> c_highestRecord = new HashMap<String,Double>();
        ArrayList<Student> c_students=o_studentDAO.getAllStudent();
        for(Student o_student: c_students){
            ArrayList<Result>o_results=o_resultDAO.getResultsByStudentID(o_student.getStudentID());
            double d_highestGrade =getHighestResutlt(o_results);
            o_student.setResults(o_results);
            String str_departmentID = o_student.getDepartmentID();
            if(c_results.containsKey(str_departmentID)){
                if(c_highestRecord.get(str_departmentID)==d_highestGrade)
                    c_results.get(str_departmentID).add(o_student);
                else if(c_highestRecord.get(str_departmentID)<d_highestGrade) {
                    c_highestRecord.put(str_departmentID,d_highestGrade);
                    c_results.get(str_departmentID).clear();
                    c_results.get(str_departmentID).add(o_student);
                }
            }else{
                c_results.put(str_departmentID, new ArrayList<Student>());
                c_highestRecord.put(str_departmentID,d_highestGrade);
                c_results.get(str_departmentID).add(o_student);
            }
        }

        JSONObject js_results = departmentStudentToJSON(c_results);
        b_success=true;
        if(b_success){
            js_response.put("success",true);
            js_response.put("results",js_results);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getSortedStudent(JSONObject js_data) throws JSONException {
        JSONObject js_response = new JSONObject();
        ArrayList<Student> c_students=o_studentDAO.getAllStudent();
        for(Student o_student: c_students){
            ArrayList<Result>o_results=o_resultDAO.getResultsByStudentID(o_student.getStudentID());
            o_student.setResults(o_results);
        }

        c_students.sort((s1,s2)->{
            if(s1 instanceof RegularStudent && s2 instanceof ServiceStudent)
                return 1;
            else if (s1 instanceof ServiceStudent && s2 instanceof RegularStudent)
                return -1;
            else{
                return s2.getAdmissionYear()-s1.getAdmissionYear();
            }
        });

        JSONArray js_students = studentsToJSON(c_students);
        if(js_students!=null){
            js_response.put("success",true);
            js_response.put("students",js_students);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }

    public JSONObject getDepartmentStatisticByYear(JSONObject js_data) throws JSONException {
        JSONObject js_response = new JSONObject();
        boolean b_success = false;
        HashMap<String,HashMap<Integer,Integer>> c_results=o_studentDAO.getDepartmentStatisticByYear();
        JSONObject js_results = departmentStatisticToJSON(c_results);
        b_success=true;
        if(b_success){
            js_response.put("success",true);
            js_response.put("results",js_results);
        }else{
            js_response.put("success",false);
        }
        return js_response;
    }
}
