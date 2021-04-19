import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleFileServer {

    public final static int SOCKET_PORT = 13267;  // you may change this
    public final static String FILE_TO_SEND = "c:/temp/source.pdf";  // you may change this

    public static void main(String[] args) throws IOException {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        ServerSocket servsock = null;

    servsock = new ServerSocket(SOCKET_PORT);
        while (true) {
            Socket sock = null;

            try {

                sock = servsock.accept();
                System.out.println("Accepted connection");
            } catch (IOException e) {

                throw new RuntimeException("Error accepting client connection", e);
            }
            new Thread(
                    new WorkerRunnable(
                            sock, "Multithreaded Server")
            ).start();
        }
    }
}