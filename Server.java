import java.net.*; //For socket things
import java.io.*; //For IO things

public class Server {
    public static void main(String args[]) {
        int portNum = Integer.parseInt(args[0]); //can be something i.e. 4999
        try {
            //Testing Socket Connection 
            ServerSocket serverSocket = new ServerSocket(portNum);
            System.out.println("Starting Server.");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client Connected.");
            //InputStream to read from client
            InputStreamReader input = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader inputBR = new BufferedReader(input);
            //String line = "";
            String line = inputBR.readLine();

            while (line != null) {
                //System.out.println("Client: " + line);
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream());
                output.println(line);
                output.flush();
                line = inputBR.readLine();
            }
            clientSocket.close();
            inputBR.close();

        }
        catch (IOException e) {
            System.out.println("An Error Has Occured: " + e);
        }
    }
}

//Ignore rn
/*
    String str = inputBR.readLine();
    System.out.println("Client: " + str);
    //To write to the client
    PrintWriter output = new PrintWriter(clientSocket.getOutputStream());
    output.println("yo"); //Server sends to the client
    output.flush(); //flushes the stream
*/