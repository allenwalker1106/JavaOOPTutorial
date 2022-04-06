package Entity;

import java.util.ArrayList;
import java.util.Date;

public class ServiceStudent extends Student{
    private String str_trainingSite;

    public ServiceStudent(int i_studentID, String str_name, Date o_birthDate, int i_admissionYear, double d_admissionGrade, ArrayList<Result> c_results, String str_trainingSite) {
        super(i_studentID, str_name, o_birthDate, i_admissionYear, d_admissionGrade, c_results);
        this.str_trainingSite = str_trainingSite;
    }

    public ServiceStudent(int i_studentID, String str_name, Date o_birthDate, int i_admissionYear, double d_admissionGrade, String str_trainingSite) {
        super(i_studentID, str_name, o_birthDate, i_admissionYear, d_admissionGrade);
        this.str_trainingSite = str_trainingSite;
    }

    public String getTrainingSite() {
        return str_trainingSite;
    }

    public void setTrainingSite(String str_trainingSite) {
        this.str_trainingSite = str_trainingSite;
    }

    @Override
    public String toString() {
        return "ServiceStudent{" + '\n' +
                '\t' + "student="+super.toString()+'\n'+
                '\t' + "str_trainingSite='" + str_trainingSite + "\'," + '\n' +
                "}\n";
    }
}
