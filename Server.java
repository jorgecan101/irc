import java.net.*; //For socket things
import java.io.*; //For IO things
import java.util.*; //For Scanner

public class Server {

    public Set<String> userNames = new HashSet<>();
    public Set<HandleClient> addClients = new HashSet<>();


    public Server(int portNum) {
        portNum = 5000;
        start();
    }
    public static void main(String args[]) {
        //int portNum = Integer.parseInt(args[0]); //can be something i.e. 4999
        //add the users with a hash table
        start();
    }
    public void start() {
        try {

            //Testing Socket Connection 
            ServerSocket serverSocket = new ServerSocket(portNum);
            System.out.println("Starting Server.");
            while (true) {
                //for multiple users
                Socket clientSocket = serverSocket.accept();
                System.out.println("New User Connected");
                //create and run a thread for the new user
                HandleClient newClient = new HandleClient(clientSocket);
                //userNames.add()
                addClients.add(newClient); //add our client to our hashset
                newClient.start(); //start the thread
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}

/*
            //TODO: For being able to contiously accept clients, we can do a 
            //while(true) loop around clientSocket in order to accept more clients
            //Aside: To get this to work right, we need to use multiple threads for 
            //multiple users
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client Connected.");
     
            Scanner input = new Scanner(clientSocket.getInputStream());
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream());
            
            //This seems to work as of now
            while (input.hasNext()) {
               // System.out.println("I am in here ");
               String line = input.nextLine();
                if ("quit".equalsIgnoreCase(line)) {
                //if (line == "quit") {
                    //currently is the user types quit then it will automatically close the loop
                    //System.out.println("User has left the chat"); //this does not appear for some reason?
                    break;
                }
                System.out.println("Client: " + line); //shows server what client said
                String msg = "Client: " + line;
                output.println(msg);
                output.flush();
            }
            clientSocket.close();
            input.close();
            serverSocket.close();
            output.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/