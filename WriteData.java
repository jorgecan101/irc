import java.net.*; //For socket things
import java.io.*; //For IO things
import java.util.*; //For Scanner

public class WriteData extends Thread {
    private PrintWriter output;
    private Socket socket;
    private Client client;

    public WriteData(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;

        try {
            output = new PrintWriter(socket.getOutputStream());
        }
         catch (IOException e) {
            e.printStackTrace();
         }
    }

    @Override
    public void run() {
        Console console = System.console();

        String userName = console.readLine("\nEnter your name: ");
        client.setUserName(userName);
        output.println(userName);
        output.flush();

        String text;

        do {
            text = console.readLine("<" + userName + ">: ");
            output.println(text);
            output.flush();
        } while (!text.equals("/quit"));

        try {
            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
