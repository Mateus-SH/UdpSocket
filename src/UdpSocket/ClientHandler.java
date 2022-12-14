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
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler extends Thread {
    
    private DatagramSocket serverSocket;
    private DatagramPacket clientPacket;
    
    public ClientHandler(DatagramSocket serverSocket, DatagramPacket clientPacket)
    {
        this.serverSocket = serverSocket;
        this.clientPacket = clientPacket;
    }
    
    
    @Override
    public void run()
    {
        try{
            InetAddress ip = this.clientPacket.getAddress();
            int port = this.clientPacket.getPort();
            String data = new String(this.clientPacket.getData());
            String htmlPath = "C:\\Users\\mateu\\Documents\\NetBeansProjects\\UdpSocket\\src\\UdpSocket\\index.html";
            String linha , string2send = "";
            long buffersize = new File(htmlPath).length();
            BufferedReader br = new  BufferedReader(new FileReader(htmlPath));        
            byte [] data2send = null;
            
            System.out.println("Cliente " + ClientHandler.currentThread().getName() + " - Enviou Pacote");
            System.out.println("Mensagem: " + data);
            while((linha = br.readLine()) != null)
            {
                string2send = string2send + (linha + "\n");
            }
            System.out.println(string2send);
            System.out.println(ip + ":" + port);
            br.close();
            data2send = string2send.getBytes();
            
            DatagramPacket packet2client = new DatagramPacket(data2send, data2send.length, ip, port);
            System.out.println("Servidor Enviando Pacote...");
            this.serverSocket.send(packet2client);
            ClientHandler.sleep(5000);
        } catch(IOException e){
            e.getStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
