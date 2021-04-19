import java.io.*;
import java.net.*;

public class Client {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port){
        try {
            clientSocket = new Socket(ip, port);        // try to connect to a socket
            out = new PrintWriter(clientSocket.getOutputStream(), true);    // outputstream for the data going to the server
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  // inputstream for the data coming from the server
        }
        catch (Exception e){
            System.out.printf("Something went wrong: " + e.toString());
        }
    }

    public String sendMessage(String message){
        try {
            out.println(message);
            String respons = in.readLine();
            return respons;
        }
        catch (Exception e) {
            System.out.println("Something went wrong: " + e.toString());
            return "Oops";
        }
    }

    public void stop(){
        try {
            in.close();     // close the inputstream
            out.close();    // close the outputstream
            clientSocket.close();   // end the connection
        }
        catch (Exception e) {
            System.out.println("Something went wrong: " + e.toString());
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) return;
        Client client = new Client();
        client.startConnection(args[0], Integer.parseInt(args[1]));

        if (args[2] != null){
            String returnmsg = client.sendMessage(args[2]);
            System.out.println(returnmsg);
        }

        client.stop();
    }

}
