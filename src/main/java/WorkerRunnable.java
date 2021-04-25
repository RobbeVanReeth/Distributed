
import java.io.*;
import java.net.Socket;

public class WorkerRunnable implements Runnable {

    protected Socket clientSocket = null;
    protected String filepath = null;

    public WorkerRunnable(Socket clientSocket, String filepath) {
        this.clientSocket = clientSocket;
        this.filepath = filepath;
    }

    public void run() {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            try {
                // send file
                File myFile = new File(filepath);
                byte[] mybytearray = new byte[(int) myFile.length()];
                fis = new FileInputStream(myFile);
                bis = new BufferedInputStream(fis);
                bis.read(mybytearray, 0, mybytearray.length);
                os = clientSocket.getOutputStream();
                System.out.println("Sending " + filepath + "(" + mybytearray.length + " bytes)");
                long time = System.currentTimeMillis();
                os.write(mybytearray, 0, mybytearray.length);
                os.flush();
                System.out.println("Request processed: " + time);
            } finally {
                if (bis != null) bis.close();
                if (os != null) os.close();
                if (clientSocket != null) clientSocket.close();
            }
        }
        catch (Exception e) {
            System.out.println("Something went wrong");
        }
    }
}