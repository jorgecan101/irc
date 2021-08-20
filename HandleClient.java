import java.net.*; //For socket things
import java.io.*; //For IO things
import java.util.*; //For Scanner


public class HandleClient extends Thread {

    private final Socket clientSocket;

    public HandleClient(Socket clientSocket) {
        this.clientSocket = clientSocket;

    }

    @Override
    public void run() {
        //do all our thread things 
        try {
            Scanner input = new Scanner(clientSocket.getInputStream());
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream());
            System.out.println("we here");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}