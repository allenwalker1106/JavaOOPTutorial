package Model;

public class NewsPaper extends Document{
    private int i_publishDate;

    public NewsPaper(String str_id, String str_publisher, int i_publishCount, DocumentType e_type, int i_publishDate) {
        super(str_id, str_publisher, i_publishCount, e_type);
        this.i_publishDate = i_publishDate;
    }

    public int getPublishDate() {
        return i_publishDate;
    }

    public void setPublishDate(int i_publishDate) {
        this.i_publishDate = i_publishDate;
    }
}
