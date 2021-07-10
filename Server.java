import java.net.*; //For socket things
import java.io.*; //For IO things

public class Server {
    public static void main(String args[]) {
        int portNum = Integer.parseInt(args[0]); //can be something i.e. 4999
        try {

            
            //Testing Socket Connection 
            ServerSocket serverSocket = new ServerSocket(portNum);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client Connected.");
            //InputStream to read from client
            InputStreamReader input = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader inputBR = new BufferedReader(input);
            String line = "";
            while (line != null) {
                line = inputBR.readLine();
                System.out.println("Client: " + line);

            }
            clientSocket.close();
            inputBR.close();
            /*
            String str = inputBR.readLine();
            System.out.println("Client: " + str);
            //To write to the client
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream());
            output.println("yo"); //Server sends to the client
            output.flush(); //flushes the stream
            */
        }
        catch (IOException e) {
            System.out.println("An Error Has Occured: " + e);
        }
    }
}