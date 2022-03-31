package Model;

public class Worker extends Officer {
    private int i_rank;

    public Worker(String str_name, String str_gender, String str_address, int i_age, Position e_position, int i_rank) {
        super(str_name, str_gender, str_address, i_age, e_position);
        this.i_rank = i_rank;
    }

    public int getRank() {
        return i_rank;
    }

    public void setRank(int i_rank) {
        this.i_rank = i_rank;
    }

    public String toString() {
        return super.toString()+
                "Rank: "+ i_rank+"\n\n";
    }

}
