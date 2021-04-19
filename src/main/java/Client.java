import java.io.*;
import java.net.*;

public class Client {

    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port){
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
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
            in.close();
            out.close();
            clientSocket.close();
        }
        catch (Exception e) {
            System.out.println("Something went wrong: " + e.toString());
        }
    }

}
