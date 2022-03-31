package Model;

public class Magazine extends Document{
    private int i_publishNumber;
    private int i_publishMonth;

    public Magazine(String str_id, String str_publisher, int i_publishCount, DocumentType e_type, int i_publishNumber, int i_publishMonth) {
        super(str_id, str_publisher, i_publishCount, e_type);
        this.i_publishNumber = i_publishNumber;
        this.i_publishMonth = i_publishMonth;
    }

    public int getPublishNumber() {
        return i_publishNumber;
    }

    public void setPublishNumber(int i_publishNumber) {
        this.i_publishNumber = i_publishNumber;
    }

    public int getPublishMonth() {
        return i_publishMonth;
    }

    public void setPublishMonth(int i_publishMonth) {
        this.i_publishMonth = i_publishMonth;
    }
}
