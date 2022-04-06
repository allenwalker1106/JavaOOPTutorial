package Control;

import Model.Employee;
import View.EmployeeManagerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class EmployeeController {
    private boolean b_running;
    private EmployeeManagerView o_view;
    private Hashtable<String,Employee> c_employees;
    private Hashtable<Employee.Position, HashSet<String>> c_employeePosition;

    public EmployeeController(){
        b_running = false;
        c_employees = new Hashtable<>();
        c_employeePosition=new Hashtable<>();
        o_view = new EmployeeManagerView();
    }

    public void start(){
        this.b_running = true;
        while(this.isRunning()){
            this.o_view.displayManual();
            EmployeeManagerView.Option e_option= this.o_view.getInputUserOption();
            switch(e_option){
                case INSERT,EDIT:
                    Employee o_employee = this.o_view.getInputEmployeeData();
                    this.insertEmployee(o_employee);
                    break;
                case DELETE:
                    String str_id = this.o_view.getInputId();
                    this.deleteEmployee(str_id);
                    break;
                case GET_BY_ID:
                    str_id = this.o_view.getInputId();
                    this.o_view.displayEmployee(this.getEmployeeById(str_id));
                    break;
                case GET_ID_LIST:
                    Employee.Position e_type = this.o_view.getInputEmployeePosition();
                    this.o_view.displayEmployeeList(e_type,this.getEmployeeIdByPosition(e_type));
                    break;
                case HELP:
//                    this.o_view.displayHelp();
                    break;
                case EXIT:
                    this.stop();
                    this.o_view.displayCloseScreen();
                    break;
                default:
                    break;
            }
        }
    }

    public HashSet<String> getEmployeeIdByPosition(Employee.Position e_type){
        return this.c_employeePosition.get(e_type);
    }

    public Employee getEmployeeById(String str_id){
        return this.c_employees.get(str_id);
    }

    public boolean deleteEmployee(String str_id){
        try {
            Employee.Position e_type = c_employees.get(str_id).getType();
            this.c_employees.remove(str_id);
            this.c_employeePosition.get(e_type).remove(str_id);
            Employee.reduceEmployeeNumber();
        }catch(NullPointerException e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public boolean insertEmployee(Employee o_employee){
        try{
            if(!this.c_employees.containsKey(o_employee.getId()))
                Employee.increaseEmployeeNumber();

            this.c_employees.put(o_employee.getId(),o_employee);
            if(this.c_employeePosition.containsKey(o_employee.getType())){
                this.c_employeePosition.get(o_employee.getType()).add(o_employee.getId());
            }else{
                this.c_employeePosition.put(o_employee.getType(),new HashSet<>());
                this.c_employeePosition.get(o_employee.getType()).add(o_employee.getId());
            }

        }catch(NullPointerException e){
            System.err.println(e);
            return false;
        }
        return true;
    }

    public void stop(){
        this.b_running = false;
    }

    public boolean isRunning() {
        return b_running;
    }

    public void setRunning(boolean b_running) {
        this.b_running = b_running;
    }

    public EmployeeManagerView getView() {
        return o_view;
    }

    public void setView(EmployeeManagerView o_view) {
        this.o_view = o_view;
    }
}
