/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author Ahmed
 */
public class Client {
Socket socket ;
DataInputStream input ;
PrintStream output;

        public Client() throws IOException
        { 
            socket = new Socket("127.0.0.1" , 5007);
            input  = new DataInputStream(socket.getInputStream());
            output = new PrintStream(socket.getOutputStream());
            output.println("Hello from client");
            String s = input.readLine();
            System.out.println(s);
            socket.close();
            input.close();
            output.close();
        }
public static void main(String[] args) throws IOException {
        Client c =  new Client();
    }
    
}
