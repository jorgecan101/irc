import java.net.*; //For socket things
import java.io.*; //For IO things
//import java.util.*; //For Scanner


public class ReadData extends Thread{
    
    private Socket socket;
    private Client client;
    //private Scanner input;
    private BufferedReader input;

    public ReadData(Socket socket, Client client) {
        this.client = client;
        this.socket = socket;

        try {
            //input = new Scanner(socket.getInputStream());
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run () {
        while (true) {
            try {
                //String response = input.nextLine();
                String response = input.readLine();
                System.out.println("\r" + response); //this here may have some fault on why it prints extra whenever someone else is saying something
                //System.out.println(response);

                //prints username after displaying server message
                if (client.getUserName() != null) {
                    System.out.print("<" + client.getUserName() + ">: ");
                }
                //System.out.println();
            }
            catch (IOException e) {
             //   System.out.println("writing down random text");
                e.printStackTrace();
                break;
            }
        }
    }
}
