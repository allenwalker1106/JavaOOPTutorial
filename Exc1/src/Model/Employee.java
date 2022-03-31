package Model;

public class Employee extends Officer {
    private String str_work;

    public Employee(String str_name, String str_gender, String str_address, int i_age, Position e_position, String str_work) {
        super(str_name, str_gender, str_address, i_age, e_position);
        this.str_work = str_work;
    }

    public String getWork() {
        return str_work;
    }

    public void setWork(String str_work) {
        this.str_work = str_work;
    }


    public String toString() {
        return super.toString()+
                "Work: "+ str_work+"\n\n";
    }
}
