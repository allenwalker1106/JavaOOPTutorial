import Client.SchoolManagerClient;

public class ClientLauncher {
    public static void main(String args[]){
        SchoolManagerClient o_schoolManagerClient = new SchoolManagerClient("localhost",1106);
        o_schoolManagerClient.start();
    }
}
