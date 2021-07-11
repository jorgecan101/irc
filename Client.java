import java.net.*; //For socket things
import java.io.*; //For IO things
import java.util.*; //For Scanner


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