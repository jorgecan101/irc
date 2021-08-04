import java.net.*; //For socket things
import java.io.*; //For IO things
import java.util.*; //For Scanner

public class HandleClient extends Thread {
    private final Socket clientSocket;
    private final ServerObj server;
    private String userName = null;
    private PrintWriter output;

    public HandleClient(ServerObj server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        handleClientSocket();
    }



    private void handleClientSocket() {
        try {
            Scanner input = new Scanner(clientSocket.getInputStream());
            this.output = new PrintWriter(clientSocket.getOutputStream());
            //userNum++;
            //This seems to work as of now
            while (input.hasNext()) {
                // System.out.println("I am in here ");
                String line = input.nextLine();
                String[] tokens = line.split(" ");
                if (tokens != null && tokens.length > 0) {
                    //should be valid
                    String cmd = tokens[0];
                    if ("logoff".equalsIgnoreCase(cmd) || "quit".equalsIgnoreCase(cmd)) { //changed from line to cmd
                        //TODO: Issue where there are multiple users, and whenever a user leaves via quit command, a SocketClosed Error appears in server terminal
                        //if (line == "quit") {
                            //currently is the user types quit then it will automatically close the loop
                            //System.out.println("User has left the chat"); //this does not appear for some reason?
                            handleLogoff();
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

    public String getUserName() {
        return userName;
    }

    private void handleLogoff() {
        //what to show whenever a user has left as well as closing the current socket
        String userOnMessage = "offline: " + userName;
        List<HandleClient> clientList = server.getClientList();
        //send current user to all other online logins
        for (HandleClient i : clientList) {
            if (!userName.equals(i.getUserName())) {
                i.send(userOnMessage);
            }
        }
    }

    private void handleLogin(PrintWriter output, String[] tokens) {
        if (tokens.length == 3) {
            //we are expecting 3 values: login, user, pass
            String userName = tokens[1];
            String password = tokens[2];

            //Not currently dealing with storing usernames and passwords, so just hardcoding them at the moment
            
            if ((userName.equals("guest") && password.equals("guest")) || (userName.equals("jim") && password.equals("password"))) {
                //we allow them to login
                String msg = "ok login";
                output.println(msg);
                output.flush();
                this.userName = userName;
                System.out.println("User logged in successfully: "  + userName);


                //TODO problem where it continues to diplay the user who is already on whenever someone else joins. This should not display since user should only see others online statuses

                List<HandleClient> clientList = server.getClientList();
                //send current user to all other online logins
                for (HandleClient i : clientList) {
                    if (i.getUserName() != null) {
                        if (!userName.equals(i.getUserName())) {
                            String onlineMessage = "online "  + i.getUserName();
                            i.send(onlineMessage);
                        }
                    }
                }
                String userOnMessage = "online " + userName;
                //sends other users to current user
                for (HandleClient i : clientList) {
                    if (!userName.equals(i.getUserName())) {
                        i.send(userOnMessage);
                    }
                }
            }
            else {
                String msg = "error login";
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
    private void send(String msg) {
        //access output of current client socket and give it to user

        if (msg != null) {
            output.println(msg);
            output.flush();
        }
    }
}