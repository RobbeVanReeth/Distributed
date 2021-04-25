import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDPClient {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        Scanner sc = new Scanner(System.in);
        DatagramSocket ds = new DatagramSocket();
        DatagramPacket outData = null;
        DatagramPacket indata = null;
        byte[] senddata, receivedata;
        InetAddress address = InetAddress.getLocalHost();

        // send port, address to server
        String hello = "Address : " + address + " , Port: " + port;
        senddata = hello.getBytes();
        outData = new DatagramPacket(senddata, senddata.length, address, port);
        ds.send(outData);

        // receive file names
        receivedata = new byte[10000];
        indata = new DatagramPacket(receivedata, receivedata.length);
        ds.receive(indata);
        String data = new String(indata.getData());
        System.out.println(data);

        // send file names for download
        System.out.println("Type filename for download: ");
        String fname = sc.nextLine();
        senddata = fname.getBytes();
        outData = new DatagramPacket(senddata, senddata.length, address, port);
        ds.send(outData);

        // receive downloadable file
        indata = new DatagramPacket(receivedata, receivedata.length);
        ds.receive(indata);
        data = new String(indata.getData());
        System.out.println(data.length());
        System.out.println(indata.getLength());
        if(data.endsWith("ERROR")){
            System.out.println("Error 404: file not found");
        } else{
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fname)), indata.getLength());
            wr.write(data, 0,indata.getLength());
            wr.close();
            System.out.println("Succesfully written to file.");
        }

        ds.close(); //close the socket

    }

}
