package Model;

public class Document {
    private String str_id;
    private String str_publisher;
    private int i_publishCount;
    private DocumentType e_type;

    public enum DocumentType{
        BOOK,
        MAGAZINE,
        NEWSPAPER,
        INVALID
    }

    public Document(String str_id, String str_publisher, int i_publishCount, DocumentType e_type) {
        this.str_id = str_id;
        this.str_publisher = str_publisher;
        this.i_publishCount = i_publishCount;
        this.e_type = e_type;
    }

    public String getId() {
        return str_id;
    }

    public void setId(String str_id) {
        this.str_id = str_id;
    }

    public String getPublisher() {
        return str_publisher;
    }

    public void setPublisher(String str_publisher) {
        this.str_publisher = str_publisher;
    }

    public int getPublishCount() {
        return i_publishCount;
    }

    public void setPublishCount(int i_publishCount) {
        this.i_publishCount = i_publishCount;
    }

    public DocumentType getType() {
        return e_type;
    }

    public void setType(DocumentType e_type) {
        this.e_type = e_type;
    }

    public String toString() {
        return "ID: "+this.str_id+"\n"+
                "Type: "+this.e_type+"\n"+
                "Publisher: "+this.str_publisher+"\n"+"" +
                "Publish Copy: "+ this.i_publishCount +"\n";
    }

}
