package Client;

import Utility.InvalidDateException;
import Utility.InvalidNameException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    private Scanner o_scanner;

    public static enum Option{
        INSERT_STUDENT,
        DELETE_STUDENT,
        UPDATE_STUDENT,
        INSERT_RESULT,
        DELETE_RESULT,
        UPDATE_RESULT,
        GET_STUDENT_BY_ID,
        GET_ALL_STUDENT,
        GET_REGULAR_STUDENT,
        GET_SERVICE_STUDENT,
        GET_STUDENT_BY_DEPARTMENT,
        GET_STUDENT_BY_TRAINING_SITE,
        GET_RESULT_BY_STUDENT,
        GET_ALL_RESULT,
        GET_RESULT_BY_SEMESTER,
        GET_REGULAR_STUDENT_RESULT,
        GET_SERVICE_STUDENT_RESULT,
        GET_RESULT_BY_DEPARTMENT,
        GET_STUDENT_TYPE,
        GET_STUDENT_RESULT_BY_SEMESTER,
        GET_DEPARTMENT_REGULAR_STUDENT_COUNT,
        GET_DEPARTMENT_TOP_ADMISSION_STUDENT,
        GET_DEPARTMENT_STUDENT_BY_GRADE,
        GET_DEPARTMENT_SERVICE_STUDENT_BY_TRAINING_SITE,
        GET_DEPARTMENT_TOP_SEMESTER_STUDENT,
        GET_SORTED_STUDENT,
        GET_DEPARTMENT_STATISTIC_BY_YEAR,
        EXIT,
        INVALID
    }

    public InputValidator(){};

    public int getValidNumberInput(String str_notification,String str_outRangeNotification, Predicate<Integer> fnc_check){
        o_scanner = new Scanner(System.in);
        int i_num=0;
        boolean b_validInput = false;
        do{
            try{
                System.out.println(str_notification);
                i_num = o_scanner.nextInt();
                if(fnc_check.test(i_num)){
                    b_validInput=true;
                }else{
                    System.err.println(str_outRangeNotification);
                }
            }catch(InputMismatchException e){
                System.err.println("[Err] Invalid Input data type");
                System.err.println("[Err] Input must be a number ");
                o_scanner.nextLine();
            }
        }while(!b_validInput);

        return i_num;
    }

    public double getValidDoubleInput(String str_notification,String str_outRangeNotification, Predicate<Double> fnc_check){
        o_scanner = new Scanner(System.in);
        double i_num=0;
        boolean b_validInput = false;
        do{
            try{
                System.out.println(str_notification);
                i_num = o_scanner.nextDouble();
                if(fnc_check.test(i_num)){
                    b_validInput=true;
                }else{
                    System.err.println(str_outRangeNotification);
                }
            }catch(InputMismatchException e){
                System.err.println("[Err] Invalid Input data type");
                System.err.println("[Err] Input must be a number ");
                o_scanner.nextLine();
            }
        }while(!b_validInput);

        return i_num;
    }

    public String getValidStringInput(String str_notification){
        o_scanner = new Scanner(System.in);
        boolean b_validInput =false;
        String str_inputString="";
        while(!b_validInput){
            try{
                System.out.println(str_notification);
                str_inputString = o_scanner.nextLine();
                if(!str_inputString.trim().equals("")){
                    b_validInput=true;
                }
            }catch(NullPointerException e){
                System.err.println("[Err] "+"Null String passed");
            }
        }
        return str_inputString;
    }

    public Option getInputUserOption(){
        o_scanner = new Scanner(System.in);
        int i_optionLength = Option.values().length-1;
        Option e_option= Option.INVALID;
        do{
            int i_inputOption=getValidNumberInput("","[Err] Option should be in range from 1-"+i_optionLength+": ",(n)->n>0&&n<=i_optionLength);
            e_option = Option.values()[i_inputOption-1];
        }while(e_option == Option.INVALID);
        return e_option;
    }

    public String getInputStudentType(){
        int i_option = getValidNumberInput("Please input student type: \n1. Regular Student\t2. Service Student","[Err] Option should be in range from 1-2: ",(n)->n>0&&n<=2);
        if(i_option==1)
            return "regular";
        return "service";
    }

    public int getInputStudentID(){
        return getValidNumberInput("Please input student ID:","[Err] Student ID should be positive number : ",(n)->n>0);
    }

    public String getInputName(){
        String str_name=null;
        boolean b_valid = false;
        do{
            str_name = getValidStringInput("Please Enter Full Name: ");
            try{
                b_valid=checkNameValid(str_name);
            }catch(InvalidNameException e){
                System.err.println(e);
            }catch(NullPointerException e){
                System.err.println(e);
            }
        }while(!b_valid);
        return str_name;
    }

    public boolean checkNameValid(String str_name) throws InvalidNameException {
        String str_pattern = "[^a-zA-Z\\s]";
        if(this.checkStringValidPattern(str_name,str_pattern)) {
            throw  new InvalidNameException();
        }
        return true;
    }

    public boolean checkStringValidPattern(String str_line,String str_pattern){
        Pattern pattern = Pattern.compile(str_pattern);
        Matcher matcher = pattern.matcher(str_line);
        return matcher.find();
    }

    public Date getInputDate(){
        String str_date=null;
        boolean b_valid = false;
        Date o_date= null;
        do{
            str_date = getValidStringInput("Please Enter Date: ");
            try{
                b_valid=checkDateValid(str_date);
            }catch(InvalidDateException e){
                System.err.println(e);
            }catch(NullPointerException e){
                System.err.println(e);
            }
        }while(!b_valid);
        String str_formatDate = formatDate(str_date);
        try{
            o_date= new SimpleDateFormat("dd/MM/yyyy").parse(str_formatDate);
        }catch(ParseException e){
            System.err.println(e);
        }
        return o_date;
    }

    String formatDate(String str_date){
        String str_formatDate = "";
        String str_pattern = "^(\\d{2})[-\\/ ](\\d{2})[-\\/ ](\\d{4})$";
        Pattern o_pattern = Pattern.compile(str_pattern);
        Matcher o_matcher = o_pattern.matcher(str_date);
        if(o_matcher.find())
        {
            try {
                str_formatDate += o_matcher.group(1) + "/" + o_matcher.group(2) + "/" + o_matcher.group(3);
            }catch(IndexOutOfBoundsException e){
                System.err.println(e);
            }
        }
        return str_formatDate;
    }

    public boolean checkDateValid(String str_date) throws InvalidDateException {
        String str_pattern = "^(0[1-9]|[1-2][0-9])[ \\/-](02)[ \\/-](\\d{4})$|^(0[1-9]|[1-2][0-9]|30)[ \\/-](0[469]|11)[ \\/-](\\d{4})$|^(0[1-9]|[1-2][0-9]|30|31)[ \\/-](0[13578]|10|12)[ \\/-](\\d{4})$";
        if(this.checkStringValidPattern(str_date,str_pattern)) {
            return true;
        }
        throw  new InvalidDateException();
    }

    public int getInputYear(String str_year){
        return getValidNumberInput("Please input "+str_year+" year:","[Err] "+str_year+" should be positive number : ",(n)->n>0);
    }

    public double getInputGrade(String str_grade, double d_bound){
        return getValidDoubleInput("Please input student"+str_grade+"Grade:","[Err] "+str_grade+" Grade must be positive number : ",(n)->n>=0&&n<=d_bound);
    }

    public int getInputSemester(){
        return getValidNumberInput("Please input semester: \n1. First Semester\t2. Second Semester","[Err] Semester must in range 1-2: ",(n)->n>0&&n<=2);
    }

    public String getInputDepartmentID(){
        int i_option = getValidNumberInput(
"Please input student type: \n" +
            "1. Cong Nghe Thong Tin    2. Dien Tu Vien Thong\n"+
            "3. Vat Ly Ky Thuat        4. Cong Nghe NANO\n"+
            "5. Co Hoc Ky Thuat        6.Tu Dong Hoa\n"+
            "7. Cong Nghe Nong Nghiep  8. Cong Nghe Hang Khong Vu Tru",
"[Err] Option should be in range from 1-8: ",(n)->n>0&&n<=8);
        switch(i_option){
            case 1->{return "CNTT";}
            case 2->{return "DTVT";}
            case 3->{return "VLKT";}
            case 4->{return "CNN";}
            case 5->{return "CHKT";}
            case 6->{return "TDH";}
            case 7->{return "CNNN";}
            case 8->{return "CNHKVT";}
            default-> {return "";}
        }
    }

    public String getInputTrainingSite(){
        int i_option = getValidNumberInput("Please input Training Site: \n1. Ha Noi\t2. Ho Chi Minh\t3. Da Nang","[Err] Option should be in range from 1-3: ",(n)->n>0&&n<=3);
        switch(i_option){
            case 1->{return "HN";}
            case 2->{return "HCM";}
            case 3->{return "DN";}
            default-> {return "";}
        }
    }

    public int getInputSemesterID(){
        int i_year = this.getInputYear("Semester");
        int i_semester = this.getInputSemester();
        int i_semesterID = i_year*10+i_semester;
        return i_semesterID;
    }

    public JSONArray getInputResults()                  throws JSONException{
        JSONArray js_data = new JSONArray();
        int i_totalResult = getValidNumberInput("Please input student total result set:","[Err]result set  must be positive number : ",(n)->n>=0);
        for(int i=0;i<i_totalResult;i++){
            js_data.put(this.getInputResult());
        }
        return js_data;
    }

    public JSONObject getInputResult()                  throws JSONException{
        JSONObject js_data = new JSONObject();
        js_data.put("semesterID",this.getInputSemesterID());
        js_data.put("averageGrade",this.getInputGrade("Average Grade",10));
        return js_data;
    }

    public JSONObject getInsertStudent()                throws JSONException{
        JSONObject js_data= new JSONObject();
        js_data.put("type",this.getInputStudentType());
        js_data.put("studentID",this.getInputStudentID());
        js_data.put("studentName",this.getInputName());
        js_data.put("studentDateOfBirth",this.getInputDate());
        js_data.put("studentAdmissionYear",this.getInputYear("Admission"));
        js_data.put("studentAdmissionGrade",this.getInputGrade("Admission",30));
        js_data.put("departmentID",this.getInputDepartmentID());
        if(js_data.get("type").toString().equalsIgnoreCase("service")){
            js_data.put("trainingSite",this.getInputTrainingSite());
        }
        js_data.put("results",this.getInputResults());
        return js_data;
    }

    public JSONObject getDeleteStudent()                throws JSONException{
        JSONObject js_data= new JSONObject();
        js_data.put("studentID",this.getInputStudentID());
        return js_data;
    }

    public JSONObject getUpdateStudent()                throws JSONException{
        JSONObject js_data= new JSONObject();
        js_data.put("type",this.getInputStudentType());
        js_data.put("studentID",this.getInputStudentID());
        js_data.put("studentName",this.getInputName());
        js_data.put("studentDateOfBirth",this.getInputDate());
        js_data.put("studentAdmissionYear",this.getInputYear("Admission"));
        js_data.put("studentAdmissionGrade",this.getInputGrade("Admission",30));
        js_data.put("departmentID",this.getInputDepartmentID());
        if(js_data.get("type").toString().equalsIgnoreCase("service")){
            js_data.put("trainingSite",this.getInputTrainingSite());
        }
        return js_data;
    }

    public JSONObject getInsertResult()                 throws JSONException{
        JSONObject js_data = new JSONObject();
        js_data.put("studentID",this.getInputStudentID());
        js_data.put("results",this.getInputResults());
        return js_data;
    }

    public JSONObject getDeleteResult()                 throws JSONException {
        JSONObject js_data= new JSONObject();
        js_data.put("studentID",this.getInputStudentID());
        js_data.put("semesterID",this.getInputSemesterID());
        return js_data;
    }

    public JSONObject getUpdateResult()                 throws JSONException{
        JSONObject js_data= new JSONObject();
        js_data.put("studentID",this.getInputStudentID());
        js_data.put("results",this.getInputResults());
        return js_data;
    }

    public JSONObject getStudentByID()                  throws JSONException{
        JSONObject js_data= new JSONObject();
        js_data.put("studentID",this.getInputStudentID());
        return js_data;
    }

    public JSONObject getAllStudent()                   throws JSONException{
        JSONObject js_data= new JSONObject();
        return js_data;
    }

    public JSONObject getRegularStudent()               throws JSONException{
        JSONObject js_data= new JSONObject();
        return js_data;
    }

    public JSONObject getServiceStudent()               throws JSONException{
        JSONObject js_data= new JSONObject();
        return js_data;
    }

    public JSONObject getStudentByDepartment()          throws JSONException{
        JSONObject js_data= new JSONObject();
        js_data.put("departmentID",this.getInputDepartmentID());
        return js_data;
    }

    public JSONObject getStudentByTrainingSite()        throws JSONException{
        JSONObject js_data= new JSONObject();
        js_data.put("trainingSite",this.getInputTrainingSite());
        return js_data;
    }

    public JSONObject getResultByStudent()              throws JSONException{
        JSONObject js_data= new JSONObject();
        js_data.put("studentID",this.getInputStudentID());
        return js_data;
    }

    public JSONObject getAllResult()                    throws JSONException{
        JSONObject js_data= new JSONObject();
        return js_data;
    }

    public JSONObject getResultBySemester()             throws JSONException{
        JSONObject js_data= new JSONObject();
        js_data.put("semesterID",this.getInputSemesterID());
        return js_data;
    }

    public JSONObject getRegularStudentResult()         throws JSONException{
        JSONObject js_data= new JSONObject();
        return js_data;
    }

    public JSONObject getServiceStudentResult()         throws JSONException{
        JSONObject js_data= new JSONObject();
        return js_data;
    }

    public JSONObject getResultByDepartment()           throws JSONException{
        JSONObject js_data= new JSONObject();
        js_data.put("departmentID",this.getInputDepartmentID());
        return js_data;

    }

    public JSONObject getStudentType()                  throws JSONException{
        JSONObject js_data= new JSONObject();
        js_data.put("studentID",this.getInputStudentID());
        return js_data;
    }

    public JSONObject getStudentResultBySemester()      throws JSONException{
        JSONObject js_data= new JSONObject();
        js_data.put("studentID",this.getInputStudentID());
        js_data.put("semesterID",this.getInputSemesterID());
        return js_data;
    }

    public JSONObject getDepartmentRegularStudentCount() throws JSONException {
        JSONObject js_data= new JSONObject();
        return js_data;
    }

    public JSONObject getDepartmentTopAdmissionStudent() throws JSONException{
        JSONObject js_data= new JSONObject();
        return js_data;
    }

    public JSONObject getDepartmentStudentByGrade()      throws JSONException{
        JSONObject js_data= new JSONObject();
        js_data.put("averageGrade",this.getInputGrade("Average",10));
        return js_data;
    }

    public JSONObject getDepartmentServiceStudentByTrainingSite() throws JSONException{
        JSONObject js_data= new JSONObject();
        js_data.put("trainingSite",this.getInputTrainingSite());
        return js_data;
    }

    public JSONObject getDepartmentTopSemesterStudent()  throws JSONException{
        JSONObject js_data= new JSONObject();
        return js_data;
    }

    public JSONObject getSortedStudent()                 throws JSONException{
        JSONObject js_data= new JSONObject();
        return js_data;
    }

    public JSONObject getDepartmentStatisticByYear()     throws JSONException{
        JSONObject js_data= new JSONObject();
        return js_data;
    }
}
