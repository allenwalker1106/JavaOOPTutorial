package Model;

public class Book extends Document {
    private String str_authorName;
    private int i_totalPageNumber;


    public Book(String str_id, String str_publisher, int i_publishCount, DocumentType e_type, String str_authorName, int i_totalPageNumber) {
        super(str_id, str_publisher, i_publishCount, e_type);
        this.str_authorName = str_authorName;
        this.i_totalPageNumber = i_totalPageNumber;
    }

    public String getAuthorName() {
        return str_authorName;
    }

    public void setAuthorName(String str_authorName) {
        this.str_authorName = str_authorName;
    }

    public int getBookNumber() {
        return i_totalPageNumber;
    }

    public void setBookNumber(int i_totalPageNumber) {
        this.i_totalPageNumber = i_totalPageNumber;

    }


    public String toString(){
        return super.toString()+
                "Author: "+str_authorName+"\n"+
                "Total Page: "+i_totalPageNumber+"\n";
    }
}
