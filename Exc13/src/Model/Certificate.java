package Model;

import java.util.Date;

public class Certificate {
    private String str_id;
    private String str_name;
    private int i_rank;
    private Date o_date;

    public Certificate(String str_id, String str_name, int i_rank, Date o_date) {
        this.str_id = str_id;
        this.str_name = str_name;
        this.i_rank = i_rank;
        this.o_date = o_date;
    }

    public String getId() {
        return str_id;
    }

    public void setId(String str_id) {
        this.str_id = str_id;
    }

    public String getName() {
        return str_name;
    }

    public void setName(String str_name) {
        this.str_name = str_name;
    }

    public int getRank() {
        return i_rank;
    }

    public void setRank(int i_rank) {
        this.i_rank = i_rank;
    }

    public Date getDate() {
        return o_date;
    }

    public void setDate(Date o_date) {
        this.o_date = o_date;
    }

    @Override
    public String toString() {
        return "\n\t\tCertificate{" +
                "str_id='" + str_id + '\'' +
                ", str_name='" + str_name + '\'' +
                ", i_rank=" + i_rank +
                ", o_date=" + o_date +
                "}\n";
    }
}
