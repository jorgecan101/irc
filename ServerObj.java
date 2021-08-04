import java.net.*; //For socket things
import java.io.*; //For IO things
import java.util.*; //For Scanner

public class ServerObj extends Thread{ 

    private final int portNum;

    private List<HandleClient> clients = new ArrayList<>();

    public ServerObj(int portNum) {
        this.portNum = portNum;
    }
    public List<HandleClient> getClientList() {
        return clients;
    }
    //this enables us to have a collection of workers that we could iterate through and send messasges between different connections

    @Override
    public void run() {
        try {
            //Testing Socket Connection 
            ServerSocket serverSocket = new ServerSocket(portNum);
            System.out.println("Starting Server.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                //System.out.println("Client Connected.");
                //int userNum = 0;
                HandleClient handleClient = new HandleClient(ServerObj.this, clientSocket);
                clients.add(handleClient);
                handleClient.start();
                //Currently there is an issue when trying to close the server socket where a problem occurs SocketClosed Error, so i am temporarily removing the serverSocket.close()
                //serverSocket.close(); //server never closes if i don't manually do it
            }
            //serverSocket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}