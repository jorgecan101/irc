import java.net.*; //For socket things
import java.io.*; //For IO things
import java.util.*; //For Scanner

public class Server {

    public Set<String> userNames = new HashSet<>();
    public Set<HandleClient> addClients = new HashSet<>();


    public Server() {
        //portNum = 5000;
        //this.portNum = portNum;
        //start(portNum);
    }
    public static void main(String args[]) {
        //int portNum = Integer.parseInt(args[0]); //can be something i.e. 4999
        //add the users with a hash table
        int portNum = 5000;
        //start();
        //create instance of server
        Server server = new Server();
        server.start(portNum);
    }
    public void start(int portNum) {
        try {

            //Testing Socket Connection 
            ServerSocket serverSocket = new ServerSocket(portNum);
            System.out.println("Starting Server.");
            while (true) {
                //for multiple users
                Socket clientSocket = serverSocket.accept();
                System.out.println("New User Connected");
                //create and run a thread for the new user
                HandleClient newClient = new HandleClient(clientSocket, this);
                //userNames.add()
                addClients.add(newClient); //add our client to our hashset
                newClient.start(); //start the thread
            }
            //serverSocket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    //other things 

    public void broadcast(String message, HandleClient excludeUser) {
        for (HandleClient client : addClients) {
            if (client != excludeUser) {
                client.sendMessage(message);
            }
        }
    }
    

    //we want to store the username of a newly connected client
    public void addClient(String userName) {
        userNames.add(userName);
    }

    //when we want get the username
    Set<String> getUserName() {
        return this.userNames;
    }

    //check if anyone is connected. return true if there are other users connected
    boolean usersOn() {
        return !this.userNames.isEmpty();
    }
    //we want to remove people (the thread as well)
    public void removeUser(String userName, HandleClient client) {
        boolean removed  = userNames.remove(userName);
        if (removed) {
            addClients.remove(client);
            System.out.println("User: " + userName + " has left.");
        }
    }
}
