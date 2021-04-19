import java.net.*;
import java.io.*;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);      // make new serversocket on a certain port
            clientSocket = serverSocket.accept();       // accept the incoming connection

            out = new PrintWriter(clientSocket.getOutputStream(), true);    // outputstream for data going to the client
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));  // inputstream for data coming to the server

            String hello = in.readLine();
            if ("hello server".equals(hello)){      // if the server gets the message "hello server" it responds with "hello client"
                out.println("hello client");
            }
            else {
                out.println("unrecognised greeting");
            }
        }
        catch(Exception e) {
            System.out.println("Something went wrong: "+e.toString());
        }
    }

    public void stop() {
        try {
            in.close();     // close the inputstream
            out.close();    // close the outputstream
            clientSocket.close();   // close the connection to the client
            serverSocket.close();   // close the serversocket
        }
        catch(Exception e) {
            System.out.println("Something went wrong: " + e.toString());
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start(5000);
    }

}
