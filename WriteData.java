import java.net.*; //For socket things
import java.io.*; //For IO things
//import java.util.*; //For Scanner

public class WriteData extends Thread {
    private PrintWriter output;
    private Socket socket;
    private Client client;
    //private Scanner input; //testing out how to get this right

    public WriteData(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;

        try {
            output = new PrintWriter(socket.getOutputStream());
            //input = new Scanner(socket.getInputStream());

        }
         catch (IOException e) {
            e.printStackTrace();
         }
    }

    @Override
    public void run() {

       // try {
       //     Thread.sleep(1); //this fixes it though sleeping threads usually isn't a good idea :P
       // } catch (InterruptedException e1) {
       //     e1.printStackTrace();
       // }

        Console console = System.console();

        String userName = console.readLine("Enter your name: ");

        //here we should check if the username is valid or not
        
        //System.out.print("\nEnter your name: ");
        //input = 
        //String userName = input.next();

        client.setUserName(userName);
        output.println(userName);
        output.flush();

        String text;

        do {
            text = console.readLine("<" + userName + ">: ");
            //text = input.nextLine();
            //System.out.print("<" + userName + ">: " + text);
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
