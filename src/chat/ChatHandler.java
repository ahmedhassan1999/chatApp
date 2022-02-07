
package chat;
import java.io.*;
import java.util.*;
import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;


public class ChatHandler extends Thread {

DataInputStream input ;
PrintStream output;
static Vector<ChatHandler> clientvectors = new Vector<ChatHandler>();
    public ChatHandler(Socket internal) throws IOException {
        input  = new DataInputStream(internal.getInputStream());
        output = new PrintStream(internal.getOutputStream());
        clientvectors.add(this);
         start();

    }
    public void run()
    {
        while(true)
        {
        try{
                String s = input.readLine();
                sendAll(s);
            }
      catch (IOException e) 
            {  
                System.out.println(e);  
            }
            
        }
    }

    public  void sendAll(String s) {
        for(ChatHandler chat : clientvectors )
        {
            chat.output.println(s);
        }
    }
    
}
