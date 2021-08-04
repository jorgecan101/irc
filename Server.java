

public class Server {
    public static void main(String args[]) {
        //int portNum = Integer.parseInt(args[0]); //can be something i.e. 4999
        int portNum = 5000;
        ServerObj server = new ServerObj(portNum);
        server.start();
    }
}