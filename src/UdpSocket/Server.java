/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author LAR
 */
package UdpSocket;
import java.net.*;
import java.io.*;
import java.lang.Thread;

public class Server  extends Thread{
    
    private int bufferLength;
    
    
    public static void main(String [] args) throws SocketException, UnknownHostException, IOException
    {
        int port  = 5000;
        DatagramSocket server = new DatagramSocket(port);
        long sizeBytes = new File("C:\\Users\\mateu\\Documents\\GitHub\\src\\index.html").length();
        
        System.out.println("Servidor Iniciado!");
        System.out.println("Endereço do Servidor:" + InetAddress.getByName("localhost").getHostAddress() + ":" + port);
        System.out.println("Aguardando pacotes UDP...");
        
        while(true)
        {
            
            byte [] databuffer = new byte[(int) sizeBytes];
            DatagramPacket clientInfo = new DatagramPacket(databuffer, databuffer.length);
            
            server.receive(clientInfo);
            
            ClientHandler thread = new ClientHandler(server, clientInfo);
            thread.start();
            
        }
        
    }    
}
