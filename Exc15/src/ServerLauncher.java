import Server.SchoolControllerServer;

public class ServerLauncher {
    public static void main(String args[]){
        SchoolControllerServer o_server = SchoolControllerServer.getInstance();
        o_server.start();
    }
}
