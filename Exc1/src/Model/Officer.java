package Model;


public abstract class Officer {
    private String str_name;
    private String str_gender;
    private String str_address;
    private int i_age;
    private  Position e_position;

    public enum Position{
        EMPLOYEE,
        ENGINEER,
        WORKER,
        INVALID
    }


    public Officer(String str_name, String str_gender, String str_address, int i_age, Position e_position) {
        this.str_name = str_name;
        this.str_gender = str_gender;
        this.str_address = str_address;
        this.i_age = i_age;
        this.e_position = e_position;
    }

    public String getName() {
        return str_name;
    }

    public void setName(String str_name) {
        this.str_name = str_name;
    }

    public int getAge() {
        return i_age;
    }

    public void setAge(int i_age) {
        this.i_age = i_age;
    }

    public String getAddress() {
        return str_address;
    }

    public void setAddress(String str_address) {
        this.str_address = str_address;
    }

    public String getGender() {
        return str_gender;
    }

    public void setGender(String str_gender) {
        this.str_gender = str_gender;
    }

    public String toString() {
        return
                "Name: "+ str_name +"\n"+
                "Address: "+ str_address +"\n"+
                "Gender: "+ str_gender +"\n"+
                "Age: "+i_age+"\n"+
                "Position: "+e_position+"\n";
    }
}
