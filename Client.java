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


































/*
public class Client {
    public static void main(String args[]) {
        //stuff for client-side will go here
        //String hostName = args[0]; //localhost for testing
        //int portNum = Integer.parseInt(args[1]); //can be something i.e. 4999
        String hostName = "localhost";
        int portNum = 5000;

        try {
            Socket clientSocket = new Socket(hostName, portNum);
            System.out.println("Client Connected.");
            Scanner input = new Scanner(System.in);
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream());

            while (input.hasNext()) {
                String line = input.nextLine();

                if ("quit".equalsIgnoreCase(line)) {
                //if (line == "quit") {
                    //System.out.println("bye!"); //this appears
                    break;
                }
                System.out.println("You: " + line);
                output.println(line);
                output.flush();
            }
            input.close();
            output.close();
            clientSocket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/