import java.net.*; //For socket things
import java.io.*; //For IO things
//import java.util.*; //For Scanner


public class Client {
    //private String hostname;
    //private int portNum;
    private String userName;

    public Client(String hostName, int portNum) {
        //this.hostname = hostname;
        //this.portNum = portNum;
    }


    public static void main(String args[]) {
        //hardcoding these right now
        String hostName = "localhost";
        int portNum = 5000;

        Client client = new Client(hostName, portNum);
        client.start(hostName, portNum);
    }

    public void start(String hostName, int portNum) {
        try {
            Socket socket = new Socket(hostName, portNum);
            System.out.println("Connected to IRC Server");
            //try {
                new ReadData(socket, this).start();
                new WriteData(socket, this).start();
            //}
            //catch (UnknownHostException e) {
            //    e.printStackTrace();
                //e.getMessage();
            //}

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return this.userName;
    }
}