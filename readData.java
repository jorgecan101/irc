import java.net.*; //For socket things
import java.io.*; //For IO things
import java.util.*; //For Scanner

public class readData extends Thread{
    
    private Socket socket;
    private Client client;
    private Scanner input;

    public readData(Socket socket, Client client) {
        this.client = client;
        this.socket = socket;

        try {
            input = new Scanner(socket.getInputStream());
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run () {
        while (true) {
            //try {
                String response = input.nextLine();
                System.out.println("\n" + response);

                //prints username after displaying server message
                if (client.getUserName() != null) {
                    System.out.print("<" + client.getUserName() + ">: ");
                }
            //}
            //catch (IOException e) {
            //    e.printStackTrace();
            //}
        }
    }
}
