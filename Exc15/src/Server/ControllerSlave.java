package Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ControllerSlave implements Runnable{
    private final Socket o_clientSocket;
    private PrintWriter o_outputStream ;
    private BufferedReader o_inputStream;
    private RequestHandle o_requestHandle;

    public ControllerSlave(Socket o_clientSocket) {
        this.o_clientSocket= o_clientSocket;
        o_requestHandle = new RequestHandle();
    }

    public void run(){
        try{
            o_outputStream = new PrintWriter(o_clientSocket.getOutputStream(),true);
            o_inputStream = new BufferedReader(new InputStreamReader(o_clientSocket.getInputStream()));

            String str_request ;
            while((str_request = o_inputStream.readLine())!=null){
                JSONObject js_response = new JSONObject();
                try {
                    js_response = handleClientRequest(str_request);
                }catch(JSONException e) {
                    try {
                        js_response.put("result", "Invalid request!");
                    }catch(JSONException e1){
                        e1.printStackTrace();
                    }
                }
                printSlaveLog(str_request);
                o_outputStream.println(js_response.toString());
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try {
                if (o_outputStream != null)
                    o_outputStream.close();
                if (o_inputStream != null)
                    o_inputStream.close();
                if(o_clientSocket!=null)
                    o_clientSocket.close();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void printSlaveLog(String str_log){
        System.out.println("[SLAVE "+o_clientSocket.getInetAddress().getHostAddress()+"] "+str_log);
    }

    private static JSONObject toJSONObject(String str_request){
        JSONObject js_request =null;
        try{
            js_request = new JSONObject(str_request);
        }catch(JSONException e){
            return null;
        }
        return js_request;
    }

    public JSONObject handleClientRequest(String js_clientRequest) throws JSONException{
        JSONObject js_request = new JSONObject(js_clientRequest);
        JSONObject js_response = new JSONObject();
        if(js_request!=null){
            if(js_request.has("command")){
                String str_command = js_request.get("command").toString().trim().toLowerCase();
                switch(str_command){
                    case "insert_student"                                   -> {js_response = handleInsertStudent((JSONObject) js_request.get("data"));}
                    case "delete_student"                                   -> {js_response = handleDeleteStudent((JSONObject) js_request.get("data"));}
                    case "update_student"                                   -> {js_response = handleUpdateStudent((JSONObject) js_request.get("data"));}
                    case "insert_result"                                    -> {js_response = handleInsertResult((JSONObject) js_request.get("data"));}
                    case "delete_result"                                    -> {js_response = handleDeleteResult((JSONObject) js_request.get("data"));}
                    case "update_result"                                    -> {js_response = handleUpdateResult((JSONObject) js_request.get("data"));}
                    case "get_student_by_id"                                -> {js_response = handleGetStudentById((JSONObject) js_request.get("data"));}
                    case "get_all_student"                                  -> {js_response = handleGetAllStudent((JSONObject) js_request.get("data"));}
                    case "get_regular_student"                              -> {js_response = handleGetRegularStudent((JSONObject) js_request.get("data"));}
                    case "get_service_student"                              -> {js_response = handleGetServiceStudent((JSONObject) js_request.get("data"));}
                    case "get_student_by_department"                        -> {js_response = handleGetStudentByDepartment((JSONObject) js_request.get("data"));}
                    case "get_student_by_training_site"                     -> {js_response = handleGetStudentByTrainingSite((JSONObject) js_request.get("data"));}
                    case "get_result_by_student"                            -> {js_response = handleGetResultByStudent((JSONObject) js_request.get("data"));}
                    case "get_all_result"                                   -> {js_response = handleGetAllResult((JSONObject) js_request.get("data"));}
                    case "get_result_by_semester"                           -> {js_response = handleGetResultBySemester((JSONObject) js_request.get("data"));}
                    case "get_regular_student_result"                       -> {js_response = handleGetRegularStudentResult((JSONObject) js_request.get("data"));}
                    case "get_service_student_result"                       -> {js_response = handleGetServiceStudentResult((JSONObject) js_request.get("data"));}
                    case "get_result_by_department"                         -> {js_response = handleGetResultByDepartment((JSONObject) js_request.get("data"));}
                    case "get_student_type"                                 -> {js_response = handleGetStudentType((JSONObject) js_request.get("data"));}
                    case "get_student_result_by_semester"                   -> {js_response = handleGetStudentResultBySemester((JSONObject) js_request.get("data"));}
                    case "get_department_regular_student_count"             -> {js_response = handleGetDepartmentRegularCount((JSONObject) js_request.get("data"));}
                    case "get_department_top_admission_student"             -> {js_response = handleGetDepartmentTopAdmissionStudent((JSONObject) js_request.get("data"));}
                    case "get_department_student_by_grade"                  -> {js_response = handleGetDepartmentStudentByGrade((JSONObject) js_request.get("data"));}
                    case "get_department_service_student_by_training_site"  -> {js_response = handleGetDepartmentStudentByTrainingSite((JSONObject) js_request.get("data"));}
                    case "get_department_top_semester_student"              -> {js_response = handleGetDepartmentTopSemesterStudent((JSONObject) js_request.get("data"));}
                    case "get_sorted_student"                               -> {js_response = handleGetSortedStudent((JSONObject) js_request.get("data"));}
                    case "get_department_statistic_by_year"                 -> {js_response = handleGetDepartmentStatisticByYear((JSONObject) js_request.get("data"));}
                }
            }else{
                js_response.put("command","invalid");
                js_response.put("data", "Invalid request!");
            }
        }else{
            js_response.put("command","invalid");
            js_response.put("data", "Invalid request!");
        }
        return js_response;
    }

    public JSONObject handleInsertStudent(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","insert_student");
            js_response.put("data",o_requestHandle.insertStudent(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleDeleteStudent(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","delete_student");
            js_response.put("data",o_requestHandle.deleteStudent(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleUpdateStudent(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","update_student");
            js_response.put("data",o_requestHandle.updateStudent(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleInsertResult(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","insert_result");
            js_response.put("data",o_requestHandle.insertResult(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleDeleteResult(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","delete_result");
            js_response.put("data",o_requestHandle.deleteResult(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleUpdateResult(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","update_result");
            js_response.put("data",o_requestHandle.updateResult(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleGetStudentById(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_student_by_id");
            js_response.put("data",o_requestHandle.getStudentByID(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleGetAllStudent(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_all_student");
            js_response.put("data",o_requestHandle.getAllStudent(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleGetRegularStudent(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_regular_student");
            js_response.put("data",o_requestHandle.getRegularStudent(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleGetServiceStudent(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_service_student");
            js_response.put("data",o_requestHandle.getServiceStudent(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleGetStudentByDepartment(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_student_by_department");
            js_response.put("data",o_requestHandle.getStudentByDepartment(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleGetStudentByTrainingSite(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_student_by_training_site");
            js_response.put("data",o_requestHandle.getStudentByTrainingSite(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleGetResultByStudent(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_result_by_student");
            js_response.put("data",o_requestHandle.getResultByStudent(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleGetAllResult(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_all_result");
            js_response.put("data",o_requestHandle.getAllResult(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleGetResultBySemester(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_result_by_semester");
            js_response.put("data",o_requestHandle.getResultBySemester(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;

    }

    public JSONObject handleGetRegularStudentResult(JSONObject js_data){

        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_regular_student_result");
            js_response.put("data",o_requestHandle.getRegularStudentResult(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleGetServiceStudentResult(JSONObject js_data){

        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_service_student_result");
            js_response.put("data",o_requestHandle.getServiceStudentResult(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleGetResultByDepartment(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_result_by_department");
            js_response.put("data",o_requestHandle.getResultByDepartment(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;

    }

    public JSONObject handleGetStudentType(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_student_type");
            js_response.put("data",o_requestHandle.getStudentType(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleGetStudentResultBySemester(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_student_result_by_semester");
            js_response.put("data",o_requestHandle.getStudentResultBySemester(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleGetDepartmentRegularCount(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_department_regular_student_count");
            js_response.put("data",o_requestHandle.getDepartmentRegularCount(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleGetDepartmentTopAdmissionStudent(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_department_top_admission_student");
            js_response.put("data",o_requestHandle.getDepartmentTopAdmissionStudent(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleGetDepartmentStudentByGrade(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_department_student_by_grade");
            js_response.put("data",o_requestHandle.getDepartmentStudentByGrade(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleGetDepartmentStudentByTrainingSite(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_department_service_student_by_training_site");
            js_response.put("data",o_requestHandle.getDepartmentStudentByTrainingSite(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleGetDepartmentTopSemesterStudent(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_department_top_semester_student");
            js_response.put("data",o_requestHandle.getDepartmentTopSemesterStudent(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }

    public JSONObject handleGetSortedStudent(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_sorted_student");
            js_response.put("data",o_requestHandle.getSortedStudent(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;

    }

    public JSONObject handleGetDepartmentStatisticByYear(JSONObject js_data){
        JSONObject js_response = new JSONObject();
        try{
            js_response.put("command","get_department_statistic_by_year");
            js_response.put("data",o_requestHandle.getDepartmentStatisticByYear(js_data));
        }catch(JSONException e){
            e.printStackTrace();
        }
        return js_response;
    }
}

