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
        handleClientSocket();
    }

    private void handleClientSocket() {
        try {
            Scanner input = new Scanner(clientSocket.getInputStream());
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream());
            //userNum++;
            //This seems to work as of now
            while (input.hasNext()) {
                // System.out.println("I am in here ");
                String line = input.nextLine();
                String[] tokens = line.split(" ");
                if (tokens != null && tokens.length > 0) {
                    //should be valid
                    String cmd = tokens[0];
                    if ("quit".equalsIgnoreCase(cmd)) { //changed from line to cmd
                        //TODO: Issue where there are multiple users, and whenever a user leaves via quit command, a SocketClosed Error appears in server terminal
                        //if (line == "quit") {
                            //currently is the user types quit then it will automatically close the loop
                            //System.out.println("User has left the chat"); //this does not appear for some reason?
                            break;
                    }
                    else if ("login".equalsIgnoreCase(cmd)) {
                        //we want a function to handle logging in a user
                        handleLogin(output, tokens);

                    }
                    else {
                        String msg = "Unknown Command: " + cmd;
                        //System.out.println(msg);
                        //System.out.println("Client " + userNum + ": " + line); //shows server what client said
                        output.println(msg);
                        output.flush();
                    }
                        //String msg = "Client: " + line;
                }
            }
            clientSocket.close();
            input.close();
            //serverSocket.close();
            output.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleLogin(PrintWriter output, String[] tokens) {
        if (tokens.length == 2) {
            //we are expecting 3 values
            String userName = tokens[1];
            String password = tokens[2];
            
            if (userName.equals("guest") && password.equals("guest")) {
                //we allow them to login
                String msg = "ok login";
                output.println(msg);
                output.flush();
            }
            else {
                String msg = "error in login";
                output.println(msg);
                output.flush();
            }
        }
        else {
            String msg = "Invalid login entered";
            output.println(msg);
            output.flush();
        }


    }
}