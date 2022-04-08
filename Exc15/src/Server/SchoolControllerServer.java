package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SchoolControllerServer {
    private volatile static SchoolControllerServer o_instance;
    private boolean b_running;
    private ServerSocket o_serverSocket =null;
    private int i_port;

    enum Type{
        IN,
        OUT,
        ERR,
    }

    private SchoolControllerServer(){
        this.setPort(1106);
        this.configurePort();
    }

    public static synchronized SchoolControllerServer getInstance() {
        if(o_instance==null){
            o_instance = new SchoolControllerServer();
        }
        return o_instance;
    }

    public void run(){
        printServerLog("Server waiting", Type.OUT);
        try{
            while(this.b_running){
                Socket o_clientSocket = o_serverSocket.accept();
                printServerLog("Accept Client "+o_clientSocket.getInetAddress().getHostAddress(),Type.IN);
                ControllerSlave slave = new ControllerSlave(o_clientSocket);
                new Thread(slave).start();
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(o_serverSocket!=null){
                try{
                    o_serverSocket.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void start(){
        this.b_running = true;
        this.run();
    }

    public void stop(){
        this.b_running=false;
    }

    public int getPort() {
        return i_port;
    }

    public void setPort(int i_port) {
        this.i_port = i_port;
    }

    private void configurePort(){
        try{
            o_serverSocket = new ServerSocket(i_port);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void printServerLog(String str_log,Type e_type){
        if(e_type==Type.IN)
            System.out.println("[IN] "+str_log);
        else if(e_type==Type.OUT)
            System.out.println("[OUT] "+str_log);
        else
            System.out.println("[ERR] "+str_log);
    }
}
