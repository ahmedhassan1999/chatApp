
package chat;

import chatapp.ChatApp;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
ServerSocket external ;  
Socket internal ;
DataInputStream input ;
PrintStream output;
    public Server() throws IOException
    {
        external = new ServerSocket(5007);
        internal = external.accept();
        input  = new DataInputStream(internal.getInputStream());
        output = new PrintStream(internal.getOutputStream());
         String s = input.readLine();
        System.out.println(s);
        output.println("Hello from server");
        external.close();
        internal.close();
        input.close();
        output.close();

    }
 public static void main(String[] args) throws IOException {
        Server s =  new Server();
    }
    
}
