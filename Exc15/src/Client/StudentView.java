package Client;


import org.json.JSONException;
import org.json.JSONObject;

public class StudentView {
    private InputValidator o_inputStream ;

    public StudentView(){
        o_inputStream = new InputValidator();
    }

    public JSONObject getUserRequest(){
        this.displayManual();
        InputValidator.Option e_userOption = o_inputStream.getInputUserOption();
        JSONObject js_request = handleUserOption(e_userOption);
        return js_request;
    }

    public void feedServerResponse(String str_response){
        System.out.println(str_response);
    }

    public void displayInvalidRequest(){
        System.out.println("Invalid Request !");
    }

    public void displayManual(){
        System.out.println("Please pick an option to execute:");
        System.out.println("Normal IO query:");
        System.out.println("01. Insert Student              \t 07. Get Student By ID              \t 13. Get Result By Student");
        System.out.println("02. Delete Student              \t 08. Get All Student                \t 14. Get All Result");
        System.out.println("03. Update Student              \t 09. Get RegularStudent             \t 15. Get Result By Semester");
        System.out.println("04. Insert Result               \t 10. Get Service Student            \t 16. Get Regular Student Result");
        System.out.println("05. Delete Result               \t 11. Get Student By Department      \t 17. Get Service Student Result");
        System.out.println("06. Update Result               \t 12. Get Student By Training Site   \t 18. Get Result By Department");
        System.out.println("Handle Process Data :");
        System.out.println("19. Get Student Type                        \t 20. Get Student Result By Semester  \t 21. Get Department Regular Student Count");
        System.out.println("22. Get Department Top Admission Student    \t 23. Get Department Student By Grade \t 24. Get Department Service Student By Training Site");
        System.out.println("25. Get Department Top Semester Student     \t 26. Get Sorted Student              \t 27. Get Department Statistic By Year");
        System.out.println("Closing Program :");
        System.out.println("28. Exit");
        System.out.print("Enter your option: ");
    }

    public JSONObject handleUserOption(InputValidator.Option e_option){
        JSONObject js_request = new JSONObject();
        switch (e_option){
            case INSERT_STUDENT                                     -> {js_request = handleInsertStudent();}
            case DELETE_STUDENT                                     -> {js_request = handleDeleteStudent();}
            case UPDATE_STUDENT                                     -> {js_request = handleUpdateStudent();}
            case INSERT_RESULT                                      -> {js_request = handleInsertResult();}
            case DELETE_RESULT                                      -> {js_request = handleDeleteResult();}
            case UPDATE_RESULT                                      -> {js_request = handleUpdateResult();}
            case GET_STUDENT_BY_ID                                  -> {js_request = handleGetStudentByID();}
            case GET_ALL_STUDENT                                    -> {js_request = handleGetAllStudent();}
            case GET_REGULAR_STUDENT                                -> {js_request = handleGetRegularStudent();}
            case GET_SERVICE_STUDENT                                -> {js_request = handleGetServiceStudent();}
            case GET_STUDENT_BY_DEPARTMENT                          -> {js_request = handleGetStudentByDepartment();}
            case GET_STUDENT_BY_TRAINING_SITE                       -> {js_request = handleGetStudentByTrainingSite();}
            case GET_RESULT_BY_STUDENT                              -> {js_request = handleGetResultByStudent();}
            case GET_ALL_RESULT                                     -> {js_request = handleGetAllResult();}
            case GET_RESULT_BY_SEMESTER                             -> {js_request = handleGetResultBySemester();}
            case GET_REGULAR_STUDENT_RESULT                         -> {js_request = handleGetRegularStudentResult();}
            case GET_SERVICE_STUDENT_RESULT                         -> {js_request = handleGetServiceStudentResult();}
            case GET_RESULT_BY_DEPARTMENT                           -> {js_request = handleGetResultByDepartment();}
            case GET_STUDENT_TYPE                                   -> {js_request = handleGetStudentType();}
            case GET_STUDENT_RESULT_BY_SEMESTER                     -> {js_request = handleGetStudentResultBySemester();}
            case GET_DEPARTMENT_REGULAR_STUDENT_COUNT               -> {js_request = handleGetDepartmentRegularStudentCount();}
            case GET_DEPARTMENT_TOP_ADMISSION_STUDENT               -> {js_request = handleGetDepartmentTopAdmissionStudent();}
            case GET_DEPARTMENT_STUDENT_BY_GRADE                    -> {js_request = handleGetDepartmentStudentByGrade();}
            case GET_DEPARTMENT_SERVICE_STUDENT_BY_TRAINING_SITE    -> {js_request = handleGetDepartmentServiceStudentByTrainingSite();}
            case GET_DEPARTMENT_TOP_SEMESTER_STUDENT                -> {js_request = handleGetDepartmentTopSemesterStudent();}
            case GET_SORTED_STUDENT                                 -> {js_request = handleGetSortedStudent();}
            case GET_DEPARTMENT_STATISTIC_BY_YEAR                   -> {js_request = handleGetDepartmentStatisticByYear();}
            case EXIT                                               -> {js_request = handleExit();}
        }
        return js_request;
    }

    public JSONObject handleInsertStudent(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","insert_student");
            js_request.put("data",o_inputStream.getInsertStudent());
        }catch(JSONException e){
            e.printStackTrace();
        }

        return js_request;
    }

    public JSONObject handleDeleteStudent(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","delete_student");
            js_request.put("data",o_inputStream.getDeleteStudent());
        }catch(JSONException e){
            e.printStackTrace();
        }

        return js_request;
    }

    public JSONObject handleUpdateStudent(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","update_student");
            js_request.put("data",o_inputStream.getUpdateStudent());
        }catch(JSONException e){
            e.printStackTrace();
        }

        return js_request;

    }

    public JSONObject handleInsertResult(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","insert_result");
            js_request.put("data",o_inputStream.getInsertResult());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleDeleteResult(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","delete_result");
            js_request.put("data",o_inputStream.getDeleteResult());
        }catch(JSONException e){
            e.printStackTrace();
        }

        return js_request;
    }

    public JSONObject handleUpdateResult(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","update_result");
            js_request.put("data",o_inputStream.getUpdateResult());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetStudentByID(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_student_by_id");
            js_request.put("data",o_inputStream.getStudentByID());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetAllStudent(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_all_student");
            js_request.put("data",o_inputStream.getAllStudent());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetRegularStudent(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_regular_student");
            js_request.put("data",o_inputStream.getRegularStudent());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;

    }

    public JSONObject handleGetServiceStudent(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_service_student");
            js_request.put("data",o_inputStream.getServiceStudent());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetStudentByDepartment(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_student_by_department");
            js_request.put("data",o_inputStream.getStudentByDepartment());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetStudentByTrainingSite(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_student_by_training_site");
            js_request.put("data",o_inputStream.getStudentByTrainingSite());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetResultByStudent(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_result_by_student");
            js_request.put("data",o_inputStream.getResultByStudent());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetAllResult(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_all_result");
            js_request.put("data",o_inputStream.getAllResult());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetResultBySemester(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_result_by_semester");
            js_request.put("data",o_inputStream.getResultBySemester());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetRegularStudentResult(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_regular_student_result");
            js_request.put("data",o_inputStream.getRegularStudentResult());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetServiceStudentResult(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_service_student_result");
            js_request.put("data",o_inputStream.getServiceStudentResult());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetResultByDepartment(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_result_by_department");
            js_request.put("data",o_inputStream.getResultByDepartment());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetStudentType(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_student_type");
            js_request.put("data",o_inputStream.getStudentType());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetStudentResultBySemester(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_student_result_by_semester");
            js_request.put("data",o_inputStream.getStudentResultBySemester());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetDepartmentRegularStudentCount(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_department_regular_student_count");
            js_request.put("data",o_inputStream.getDepartmentRegularStudentCount());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetDepartmentTopAdmissionStudent(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_department_top_admission_student");
            js_request.put("data",o_inputStream.getDepartmentTopAdmissionStudent());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetDepartmentStudentByGrade(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_department_student_by_grade");
            js_request.put("data",o_inputStream.getDepartmentStudentByGrade());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetDepartmentServiceStudentByTrainingSite(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_department_service_student_by_training_site");
            js_request.put("data",o_inputStream.getDepartmentServiceStudentByTrainingSite());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetDepartmentTopSemesterStudent(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_department_top_semester_student");
            js_request.put("data",o_inputStream.getDepartmentTopSemesterStudent());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetSortedStudent(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_sorted_student");
            js_request.put("data",o_inputStream.getSortedStudent());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public JSONObject handleGetDepartmentStatisticByYear(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","get_department_statistic_by_year");
            js_request.put("data",o_inputStream.getDepartmentStatisticByYear());
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;

    }

    public JSONObject handleExit(){
        JSONObject js_request = new JSONObject();
        try{
            js_request.put("command","exit");
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_request;
    }

    public void displayCloseScreen(){
        System.out.println("Goodbye!");
    }
}
