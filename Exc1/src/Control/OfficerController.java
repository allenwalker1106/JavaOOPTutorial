package Control;

import Model.Officer;
import View.ManagerView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class OfficerController {
    private Hashtable<String, ArrayList<Officer>> c_officers;
    private ManagerView o_view;
    private boolean b_running = false;

    public OfficerController(){
        c_officers = new Hashtable<> ();
        o_view = new ManagerView();
    }

    public boolean isRunning(){
        return this.b_running;
    }

    public void start(){
        this.b_running = true;
        while(this.isRunning()){
            this.o_view.displayManual();
            ManagerView.Option e_option= this.o_view.getUserOption();
            switch(e_option){
                case INSERT:
                    Officer officer = this.o_view.getOfficerDataInput();
                    this.insertOfficer(officer);
                    break;
                case DISPLAY_ALL:
                    this.o_view.displayAllOfficer(this.c_officers);
                    break;
                case DISPLAY_BY_NAME:
                    String str_officerName = this.o_view.getInputOfficerName();
                    this.o_view.displayOfficer(this.getOfficerByName(str_officerName));
                    break;
                case HELP:
                    this.o_view.displayHelp();
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

    public Boolean insertOfficer(Officer o_officer){
        try {
            if(c_officers.containsKey(o_officer.getName())){
                c_officers.get(o_officer.getName()).add(o_officer);
            }else
                c_officers.put(o_officer.getName(), new ArrayList<>(List.of(o_officer)));
            return true;
        }catch(NullPointerException e){
            return false;
        }
    }

    public ArrayList<Officer>  getOfficerByName(String name){
            return this.c_officers.get(name);
    }

    public Hashtable<String, ArrayList<Officer>> getOfficers() {
        return c_officers;
    }

    public void setOfficers(Hashtable<String, ArrayList<Officer>> c_officers) {
        this.c_officers = c_officers;
    }
}
