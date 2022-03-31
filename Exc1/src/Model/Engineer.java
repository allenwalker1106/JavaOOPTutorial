package Model;

public class Engineer extends Officer {
    private String str_major;

    public Engineer(String str_name, String str_gender, String str_address, int i_age, Position e_position, String str_major) {
        super(str_name, str_gender, str_address, i_age, e_position);
        this.str_major = str_major;
    }

    public String getMajor() {
        return str_major;
    }

    public void setMajor(String str_) {
        this.str_major = str_;
    }

    public String toString() {
        return super.toString()+
                "Major: "+ str_major+"\n\n";
    }
}
