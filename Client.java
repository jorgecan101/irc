import java.net.*; //For socket things
import java.io.*; //For IO things

public class Client {
    public static void main(String args[]) {
        //stuff for client-side will go here
        String hostName = args[0]; //localhost for testing
        int portNum = Integer.parseInt(args[1]); //can be something i.e. 4999
        try {
            //Testing Socket Connection 
            Socket clientSocket = new Socket(hostName, portNum);
            //OuputStream to write to the Server
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream());
            output.println("hello"); //Client sends hello to the server
            output.flush(); //flushes stream
            //InputStreamReader for to read from Server
            InputStreamReader input = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader inputBR = new BufferedReader(input);

            String str = inputBR.readLine();
            System.out.println("Server: " + str); //reads what the server has sent
        }
        catch (IOException e) {
            System.out.println("An Error Has Occured: " + e);
        }
    }
}