package Client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SchoolManagerClient {
    private int i_serverPort;
    private String str_host;
    public boolean b_running;
    private BufferedReader o_inputStream;
    private PrintWriter o_outputStream;
    private StudentView o_view;


    public SchoolManagerClient(String str_host,int i_serverPort){
        this.str_host = str_host;
        this.i_serverPort = i_serverPort;
        this.o_view = new StudentView();
    }

    public int getServerPort() {
        return i_serverPort;
    }

    public void setServerPort(int i_serverPort) {
        this.i_serverPort = i_serverPort;
    }

    public String getHost() {
        return str_host;
    }

    public void setHost(String str_host) {
        this.str_host = str_host;
    }

    public void start(){
        b_running = true;
        this.run();
    }

    public void run(){
        try{
            Socket o_clientSocket = new Socket(str_host,i_serverPort);
            o_outputStream = new PrintWriter(o_clientSocket.getOutputStream());
            o_inputStream = new BufferedReader(new InputStreamReader(o_clientSocket.getInputStream()));

            while(b_running){
                try {
                    JSONObject js_userRequest = this.o_view.getUserRequest();
                    if (js_userRequest.has("command")) {
                        if (js_userRequest.get("command").toString().equalsIgnoreCase("exit")) {
                            this.stop();
                        }else{
                            String str_request = js_userRequest.toString();
                            o_outputStream.println(str_request);
                            o_outputStream.flush();
                            o_view.feedServerResponse(o_inputStream.readLine());
                        }
                    }else{
                        o_view.displayInvalidRequest();
                    }

                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        o_view.displayCloseScreen();
    }

    public void stop(){
        b_running=false;
    }
}
