import java.net.*; //For socket things
import java.io.*; //For IO things
//import java.util.*; //For Scanner

public class HandleClient extends Thread {

    private final Socket clientSocket;
    private PrintWriter output;
    private BufferedReader input;
    private Server server;

    public HandleClient(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    @Override
    public void run() {
        //do all our thread things 
        try {
            //Scanner input = new Scanner(clientSocket.getInputStream());
            //PrintWriter output = new PrintWriter(clientSocket.getOutputStream());
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //trying this out with buffered reader again instead
            output = new PrintWriter(clientSocket.getOutputStream());
            //output = new PrintWriter(clientSocket.getOutputStream(), true);
            //System.out.println("we here");
            //we print our users first to let new user know who is online
            printUsers();
            //user types name and gets added
            //String userName = input.nextLine();
            String userName = input.readLine();
            server.addClient(userName);

            String serverMessage = "New User Connected: " + userName;
            server.broadcast(serverMessage, this);

            String clientMessage;
            do {
                clientMessage = input.readLine();
                //clientMessage = input.nextLine();
                serverMessage = "<" + userName + ">: " + clientMessage;
                server.broadcast(serverMessage, this);
            } while (!clientMessage.equals("/quit"));
            
            server.removeUser(userName, this);
            clientSocket.close(); 
            
            serverMessage = userName + " has left.";
            server.broadcast(serverMessage, this);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Sends a list of online users to newly connected user
    public void printUsers() {
        if (server.hasUsers()) {
            output.println("Connected Users: " + server.getUserName());
            output.flush();
        }
        else {
            output.println("No others users currently online");
            output.flush();
        }
    }

    //send message command we mentioned. Message sent to the client
    public void sendMessage(String message) {
        output.println(message);
        output.flush();
    }
}