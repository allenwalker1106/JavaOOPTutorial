package Control;

import Model.Document;
import View.LibraryView;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

public class LibraryController {
    private boolean b_running;
    private Hashtable<String, Document> c_documents;
    private Hashtable<Document.DocumentType, Set<String>> c_genres;
    private LibraryView o_view;



    public LibraryController() {
        this.b_running=false;
        this.o_view = new LibraryView();
        this.c_documents = new Hashtable<>();
        this.c_genres = new Hashtable<>();
    }

    public boolean isRunning() {
        return b_running;
    }

    public void setRunning(boolean b_running) {
        this.b_running = b_running;
    }

    public void start(){
        this.b_running = true;
        while(this.isRunning()){
            this.o_view.displayManual();
            LibraryView.Option e_option= this.o_view.getUserOption();
            switch(e_option){
                case INSERT:
                    Document o_document = this.o_view.getDocumentDataInput();
                    this.insertDocument(o_document);
                    break;
                case DELETE:
                    String str_id = this.o_view.getInputDocumentID();
                    this.deleteDocument(str_id);
                    break;
                case DISPLAY_BY_ID:
                    str_id = this.o_view.getInputDocumentID();
                    this.o_view.displayDocument(this.getDocumentByID(str_id));
                    break;
                case GET_ID_LIST:
                    Document.DocumentType e_type = this.o_view.getInputDocumentType();
                    this.o_view.displayLIstID(e_type,this.getDocumentList(e_type));
                    break;
                case EXIT:
                    this.exit();
                    this.o_view.displayCloseScreen();
                    break;
                default:
                    break;
            }
        }
    }

    public void exit(){
        this.b_running = false;
    }

    public boolean insertDocument(Document o_document){
        try{
            this.c_documents.put(o_document.getId(),o_document);
            if(this.c_genres.containsKey(o_document.getType()))
                this.c_genres.get(o_document.getType()).add(o_document.getId());
            else
                this.c_genres.put(o_document.getType(),new HashSet<String>(List.of(o_document.getId())));

        }catch(NullPointerException e){
            return false;
        }
        return true;
    }

    public boolean deleteDocument(String str_id){
        if(this.c_documents.containsKey(str_id)){
            Document o_doc = this.c_documents.get(str_id);
            Document.DocumentType type = o_doc.getType();
            this.c_documents.remove(str_id);
            this.c_genres.get(type).remove(str_id);
        }else
            return false;
        return true;
    }

    public Set<String> getDocumentList(Document.DocumentType e_type){
        return this.c_genres.get(e_type);
    }

    public Document getDocumentByID(String str_id){
        return this.c_documents.get(str_id);
    }
}
