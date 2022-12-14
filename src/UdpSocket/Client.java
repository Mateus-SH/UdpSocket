/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UdpSocket;
import java.lang.Thread;
import java.net.*;
import java.io.*;
import java.util.Scanner;


/**
 *
 * @author mateu
 */
public class Client extends Thread{
        
    public static void main(String [] args)
    {
        int i, nThreads;
        Scanner scanf = new Scanner(System.in);
        Client [] clients;
        
        System.out.println("Digite o número de clientes: ");
        nThreads = scanf.nextInt();
        clients = new Client[nThreads];

        for(i = 0; i < nThreads; i++)
        {
            clients[i] = new Client();
        }
        
        for(i = 0; i < nThreads; i++)
        {
            clients[i].start();
        }
    
    }

    @Override
    public void run()
    {
        try {
            
            DatagramSocket client = new DatagramSocket();
            InetAddress IpAdress = InetAddress.getByName("localhost");
            DatagramPacket clientPacket, responsePacket;
            Scanner scanf = new Scanner(System.in);
            FileWriter fw =  new FileWriter("C:\\Users\\mateu\\Documents\\NetBeansProjects\\UdpSocket\\src\\UdpSocket\\index"+ Client.currentThread().getName() +".html");
            String msg = "html";
            int tamMsg = msg.getBytes().length;
            byte [] dataToServer = new byte[tamMsg];
            byte [] msgOfServer = new byte[571];
            
            dataToServer = msg.getBytes();
            
            clientPacket = new DatagramPacket(dataToServer, tamMsg, IpAdress, 5000);
            System.out.println("Cliente " + Client.currentThread().getName() + " - Envia: " + msg );
            client.send(clientPacket);
            
            
            responsePacket = new DatagramPacket(msgOfServer, msgOfServer.length);
           
            client.receive(responsePacket);
            
            String response = new String(responsePacket.getData());
            
            
            fw.write(response);
            fw.close();

            System.out.println(response);
            Runtime.getRuntime().exec("cmd.exe /C start brave.exe C:\\Users\\mateu\\Documents\\NetBeansProjects\\UdpSocket\\src\\UdpSocket\\index" + Client.currentThread().getName()+ ".html");
            client.close(); 
        } catch (Exception e) {
        
            e.getStackTrace();
        }
    
    }
    
}
