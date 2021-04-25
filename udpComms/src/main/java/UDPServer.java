import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPServer {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        byte[] senddata,receivedata;

        DatagramSocket socket = new DatagramSocket(port);
        DatagramPacket out = null;
        DatagramPacket in = null;

        while(true){
            //get port, address from client
            receivedata = new byte[770000];
            in = new DatagramPacket(receivedata, receivedata.length);
            socket.receive(in);
            InetAddress srcaddress = in.getAddress();
            int srcport = in.getPort();
            String f = new String(in.getData());
            System.out.println(f);

            //Send filename to client
            String dirname = "C:/Temp/java";
            File f1 = new File(dirname);
            File direct[] = f1.listFiles();
            System.out.println("Files in directory: " + f1);
            StringBuilder sb = new StringBuilder("\n");
            int c = 0;
            for (int i=0;i< direct.length;i++){
                if(direct[i].canRead() ){ c++;}
            }
            sb.append(c+"  files found\n\n");
            for(int i=0;i<direct.length;i++){
                    sb.append(direct[i].getName() + " ,size:" +direct[i].length() + " Bytes\n");
            }
            sb.append("Enter the filename for download: ");
            senddata =(sb.toString()).getBytes();
            out = new DatagramPacket(senddata, 0, senddata.length, srcaddress, srcport);
            socket.send(out);

            //Get file name for downloading
            in = new DatagramPacket(receivedata, receivedata.length);
            String fname = new String(in.getData());
            int idx=0;
            boolean flag = false;
            for(int i=0;i< direct.length;i++){
                if(direct[i].getName().equalsIgnoreCase(fname)){
                    System.out.println("-----------");
                    idx=i;
                    flag=true;
                    break;
                }
            }
                //copy existing file
            File copy = new File(direct[idx].getAbsolutePath());
            FileReader fr = new FileReader(copy);
            BufferedReader buf = new BufferedReader(fr);
            sb = new StringBuilder();
            String s = null;
            while((s=buf.readLine())!=null  ){
                sb.append(s + "\n");
            }
            if(buf.readLine()==null){ System.out.println("File read done.");}
            senddata = (sb.toString()).getBytes();
            out = new DatagramPacket(senddata, senddata.length, srcaddress, srcport);
            socket.send(out);

        }
    }
}
